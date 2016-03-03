package com.alhazen.defiolles.alhazen.Game;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.view.Surface;

import com.alhazen.defiolles.alhazen.Game.GameObject.BlocMouvant;
import com.alhazen.defiolles.alhazen.Game.GameObject.InanimateObject;
import com.alhazen.defiolles.alhazen.Game.GameObject.MoveObject;
import com.alhazen.defiolles.alhazen.Game.GameObject.Mur;
import com.alhazen.defiolles.alhazen.Game.GameObject.Pic;
import com.alhazen.defiolles.alhazen.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by PAYS on 23/02/2016.
 */
public class Level implements Serializable{
    InanimateObject[][] level;
    ArrayList<MoveObject> moveObjects = new ArrayList<>();

    int width;
    int height;
    int caseWidth;
    int caseHeight;
    int posXJoueurDepart;
    int posYJoueurDepart;


    public Level(int width,int height, int id) {
        this.width = width;
        this.height = height;
        level = new InanimateObject[width][height];
        for (int i =0 ;i< width ; i++)
        {
            level[i][0] = new Mur(id,3,5, Mur.TypeDeMur.BOTTOM);
            level[i][height-1] = new Mur(id,3,5, Mur.TypeDeMur.TOP);
        }

        for (int i =1 ;i< height-1 ; i++)
        {
            level[0][i] = new Mur(id,3,5, Mur.TypeDeMur.RIGHT);
            level[width-1][i] = new Mur(id,3,5, Mur.TypeDeMur.LEFT);
        }
        level[0][0] = new Mur(id,3,5, Mur.TypeDeMur.BOTTOM_RIGHT_IN);
        level[0][height-1] = new Mur(id,3,5, Mur.TypeDeMur.TOP_RIGHT_IN);
        level[width-1][0] = new Mur(id,3,5, Mur.TypeDeMur.BOTTOM_LEFT_IN);
        level[width-1][height-1] = new Mur(id,3,5, Mur.TypeDeMur.TOP_LEFT_IN);
        posXJoueurDepart = width/2;
        posYJoueurDepart = height-2;
        moveObjects.add(new BlocMouvant(R.drawable.cube,1,200,200));
    }

    public int getPosXJoueurDepart() {
        return posXJoueurDepart*caseWidth;
    }

    public int getPosYJoueurDepart() {
        return posYJoueurDepart*caseHeight;
    }

    public void avanceX(MoveObject object)
    {
        for (int i=0 ;i<width ; i++) {
            for (int j = 0; j < height; j++) {
                if (level[i][j] != null) level[i][j].effectX(object);
            }
        }

    }

    public void avanceY(MoveObject object)
    {
        for (int i=0 ;i<width ; i++) {
            for (int j = 0; j < height; j++) {
                if (level[i][j] != null) level[i][j].effectY(object);
            }
        }
    }

    public boolean collisionAvecMur(MoveObject object)
    {
        for (int i=0 ;i<width ; i++) {
            for (int j = 0; j < height; j++) {
                if (level[i][j] != null && Collisions.collisionGameObjects(object,level[i][j]))
                    return true;
            }
        }
        return false;
    }
    public void initializeTexture(Resources resources, int widthEcran, int heightEcran, int orientation)
    {
        for (int i=0 ;i<width ; i++) {
            for (int j = 0; j < height; j++) {
                if (level[i][j] != null) {
                    if(orientation == Surface.ROTATION_0 || orientation == Surface.ROTATION_180)
                        level[i][j].initializeSprite(resources, i, j, widthEcran, heightEcran, width, height);
                    else
                        level[i][j].initializeSprite(resources, i, j, heightEcran, widthEcran,width , height);
                    caseWidth = level[i][j].getWidth();
                    caseHeight = level[i][j].getHeight();
                }
            }
        }

        for (MoveObject moveObject: moveObjects) {
            moveObject.initializeSprite(resources);
        }
    }

    public void initializeTexture(Resources resources)
    {
        for (int i=0 ;i<width ; i++) {
            for (int j = 0; j < height; j++) {
                if (level[i][j] != null) level[i][j].initializeSprite(resources);
            }
        }

        for (MoveObject moveObject: moveObjects) {
            moveObject.initializeSprite(resources);
        }
    }

    public void initializeOrientation(int orientation)
    {
        for (MoveObject moveObject: moveObjects) {
            moveObject.setOrientation(orientation);
        }
    }


    public void drawLevel(Canvas canvas,Paint paint)
    {
        for (int i=0 ;i<width ; i++)
        {
            for(int j = 0 ; j< height ; j++)
            {
                if(level[i][j] != null){
                    level[i][j].draw(canvas, paint);

                }
            }
        }
        for (MoveObject moveObject: moveObjects) {
            moveObject.draw(canvas, paint);
        }
    }

    public void changeDirectectionY(float val)
    {
        for (MoveObject moveObject: moveObjects ) {

            if (val < 1) {
                if (moveObject.getDirectionY() == Direction.DirectionEnum.BOTTOM) {
                    moveObject.setDirectionY(Direction.DirectionEnum.TOP);
                    moveObject.setAuSol(false);
                    moveObject.turnThis();
                }
            } else {
                if (moveObject.getDirectionY() == Direction.DirectionEnum.TOP) {
                    moveObject.setDirectionY(Direction.DirectionEnum.BOTTOM);
                    moveObject.setAuSol(false);
                    moveObject.turnThis();
                }
            }
        }
    }


    public void move(int pas,MoveObject player)
    {
        for (MoveObject moveObject: moveObjects ) {
            moveObject.whenTuchOtherMoveObject(player,this);
            moveObject.move(pas,this);

        }
    }
}
