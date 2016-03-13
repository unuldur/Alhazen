package com.alhazen.defiolles.alhazen.Game.Level;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Surface;

import com.alhazen.defiolles.alhazen.Game.Collisions;
import com.alhazen.defiolles.alhazen.Game.Direction;
import com.alhazen.defiolles.alhazen.Game.GameObject.Arriver;
import com.alhazen.defiolles.alhazen.Game.GameObject.BaseLaser;
import com.alhazen.defiolles.alhazen.Game.GameObject.BlocMouvant;
import com.alhazen.defiolles.alhazen.Game.GameObject.InanimateObject;
import com.alhazen.defiolles.alhazen.Game.GameObject.MoveObject;
import com.alhazen.defiolles.alhazen.Game.GameObject.Mur;
import com.alhazen.defiolles.alhazen.Game.GameObject.Portail;
import com.alhazen.defiolles.alhazen.Load.Load;
import com.alhazen.defiolles.alhazen.Load.SerializationLoad;
import com.alhazen.defiolles.alhazen.R;
import com.alhazen.defiolles.alhazen.Save.Save;
import com.alhazen.defiolles.alhazen.Save.SerializationSave;

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
    int decalageX;
    int decalageY;
    boolean ajoutLevel = false;
    boolean levelFinish = false;
    public Level(int width,int height, int id, Context context) {
        this.width = width;
        this.height = height;
        level = new InanimateObject[width][height];
        for (int i =0 ;i< width ; i++)
        {
            level[i][0] = new Mur(id,3,5, Mur.TypeDeMur.BOTTOM);
            level[i][4] = new Mur(id,3,5, Mur.TypeDeMur.TOP);
        }

        for (int i =1 ;i< 4 ; i++)
        {
            level[0][i] = new Mur(id,3,5, Mur.TypeDeMur.RIGHT);
            level[width-1][i] = new Mur(id,3,5, Mur.TypeDeMur.LEFT);
        }
        level[0][0] = new Mur(id,3,5, Mur.TypeDeMur.BOTTOM_RIGHT_IN);
        level[0][4] = new Mur(id,3,5, Mur.TypeDeMur.TOP_RIGHT_IN);
        level[width-1][0] = new Mur(id,3,5, Mur.TypeDeMur.BOTTOM_LEFT_IN);
        level[width-1][4] = new Mur(id,3,5, Mur.TypeDeMur.TOP_LEFT_IN);
        level[width-2][1] = new Arriver(R.drawable.arrive,1);
        level[1][1] = new BaseLaser(R.drawable.laser,8,2, Direction.DirectionEnum.RIGHT);

        Save save = new SerializationSave();


        posXJoueurDepart = width/2;
        posYJoueurDepart = 4;
        moveObjects.add(new BlocMouvant(R.drawable.cube,1,300,100));

        save.saveInanimateObjects(level, "level_1", context);
        save.saveObject("level_1_Move",context,moveObjects);
        save.savePosJoueur("level_1_Pos",context,posXJoueurDepart,posYJoueurDepart);
    }

    public Level(String name,Context context)
    {
        width = 13;
        height = 18;
        level = new InanimateObject[13][18];
        Load load = new SerializationLoad();
        level = load.loadInanimate(name,context);
        moveObjects = load.loadObject(name + "_Move", context);
        int[] pos = load.loadPosJoueur(name+"_Pos",context);
        posXJoueurDepart = pos[0];
        posYJoueurDepart = pos[1];
    }

    public Level(int numero,Context context)
    {
        Load load = new SerializationLoad();
        //level = load.loadInanimate("level_"+numero,context);
        width = 13;
        height = 18;
        //if(level == null) {
            SaveLevel.saveLevel(numero, context);
            level = load.loadInanimate("level_" + numero, context);
        //}
        moveObjects = load.loadObject("level_"+numero+"_Move",context);



    }

    public void avanceX(MoveObject object)
    {
        for (int i=0 ;i<width ; i++) {
            for (int j = 0; j < height; j++) {
                if (level[i][j] != null) level[i][j].effectX(object);
            }
        }

    }

    public void avanceY(MoveObject object) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (level[i][j] != null) level[i][j].effectY(object);
            }
        }

    }

    public void initializeTexture(Resources resources, int widthEcran, int heightEcran, int orientation)
    {
        for (int i=0 ;i<width ; i++) {
            for (int j = 0; j < height; j++) {
                if (level[i][j] != null && level[i][j].getSpriteSheet() == null) {

                    if(orientation == Surface.ROTATION_0 || orientation == Surface.ROTATION_180)
                        level[i][j].initializeSprite(resources, i, j, widthEcran, heightEcran, width, height,caseWidth,caseHeight);
                    else
                        level[i][j].initializeSprite(resources, i, j, heightEcran, widthEcran,width , height,caseWidth,caseHeight);
                    if(caseHeight == 0) {
                        caseWidth = level[i][j].getWidth();
                        caseHeight = level[i][j].getHeight();
                        decalageX = level[i][j].getDecalageX();
                        decalageY = level[i][j].getDecalageY();
                    }
                }
            }
        }
        System.out.println(decalageX +" "+ decalageY+" "+caseWidth+" "+caseHeight);
        for (MoveObject moveObject: moveObjects) {
                moveObject.initializeSprite(resources);
                moveObject.setPosition(moveObject.getPosX() * caseWidth + decalageX, moveObject.getPosY() * caseHeight + decalageY);
                moveObject.setPosXDepart(moveObject.getPosX());
                moveObject.setPosYDepart( moveObject.getPosY());
        }


        ajoutLevel = false;
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
            if(!moveObject.isAuSol()) return;
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


    public void move(int pas)
    {
        MoveObject player = moveObjects.get(0);
        for (MoveObject moveObject: moveObjects ) {
            moveObject.whenTouchOtherMoveObject(player, this);
            moveObject.move(pas, this);
            moveObject.updateFrame();

        }
    }



    public void updateFrameLevel()
    {
        for (int i=0 ;i<width ; i++) {
            for (int j = 0; j < height; j++) {
                if (level[i][j] != null){
                    level[i][j].effetSurLevel(this);
                    level[i][j].updateFrame();
                }
            }
        }
    }

    public InanimateObject exist(int posX, int posY)
    {
        return level[posX][posY];
    }

    public void addInanimateObject(int posX,int posY,InanimateObject object)
    {
        level[posX][posY] = object;
        ajoutLevel = true;
    }

    public boolean toucheGameObject(InanimateObject inanimateObject, Class classe)
    {
        for (MoveObject move:moveObjects) {
            if(Collisions.collisionGameObjects(inanimateObject, move) && move.getClass() == classe)
                return true;
        }
        return false;
    }

    public boolean toucheGameObject(InanimateObject inanimateObject)
    {
        for (MoveObject move:moveObjects) {
            if(Collisions.collisionGameObjects(inanimateObject,move))
                return true;
        }
        return false;
    }

    public void killThis(int posX,int posY)
    {

        if(level[posX][posY] != null) level[posX][posY].killMe(this);
        level[posX][posY] = null;
    }

    public void movePlayer(Direction.DirectionEnum directionEnum)
    {
        moveObjects.get(0).setDirectionX(directionEnum);
    }

    public boolean isLevelFinish() {
        return levelFinish;
    }

    public void setLevelFinish(boolean levelFinish) {
        this.levelFinish = levelFinish;
    }
}
