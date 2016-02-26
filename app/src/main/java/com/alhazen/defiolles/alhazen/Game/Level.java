package com.alhazen.defiolles.alhazen.Game;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.alhazen.defiolles.alhazen.Game.GameObject.MoveObject;
import com.alhazen.defiolles.alhazen.Game.GameObject.Mur;

import java.io.Serializable;

/**
 * Created by PAYS on 23/02/2016.
 */
public class Level implements Serializable{
    Mur[][] level;
    int width;
    int height;
    public Level(int width,int height, int id) {
        this.width = width;
        this.height = height;
        level = new Mur[width][height];
        for (int i =0 ;i< width ; i++)
        {
            level[i][0] = new Mur(id,3,5, Mur.TypeDeMur.BOTTOM);
            level[i][height-1] = new Mur(id,3,5, Mur.TypeDeMur.TOP);
        }

        for (int i =0 ;i< height-1 ; i++)
        {
            level[0][i] = new Mur(id,3,5, Mur.TypeDeMur.RIGHT);
            level[width-1][i] = new Mur(id,3,5, Mur.TypeDeMur.LEFT);
        }
        level[0][0] = new Mur(id,3,5, Mur.TypeDeMur.BOTTOM_RIGHT_IN);
        level[0][height-1] = new Mur(id,3,5, Mur.TypeDeMur.TOP_RIGHT_IN);
        level[width-1][0] = new Mur(id,3,5, Mur.TypeDeMur.BOTTOM_LEFT_IN);
        level[width-1][height-1] = new Mur(id,3,5, Mur.TypeDeMur.TOP_LEFT_IN);
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

    public void initializeTextureMurs(Resources resources,int widthEcran,int heightEcran)
    {
        for (int i=0 ;i<width ; i++) {
            for (int j = 0; j < height; j++) {
                if (level[i][j] != null) level[i][j].initializeSprite(resources, i, j, widthEcran, heightEcran, width, height);
            }
        }
    }

    public void initializeTextureMurs(Resources resources)
    {
        for (int i=0 ;i<width ; i++) {
            for (int j = 0; j < height; j++) {
                if (level[i][j] != null) level[i][j].initializeSprite(resources);
            }
        }
    }


    public void drawLevel(Canvas canvas,Paint paint)
    {
        for (int i=0 ;i<width ; i++)
        {
            for(int j = 0 ; j< height ; j++)
            {
                if(level[i][j] != null) level[i][j].draw(canvas,paint);
            }
        }
    }
}
