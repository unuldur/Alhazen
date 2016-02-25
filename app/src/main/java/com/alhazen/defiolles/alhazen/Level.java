package com.alhazen.defiolles.alhazen;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by PAYS on 23/02/2016.
 */
public class Level {
    Mur[][] level;
    int width;
    int height;
    public Level(int width,int height,Resources resources, int id,int widthEcran,int heightEcran) {
        this.width = width;
        this.height = height;
        level = new Mur[width][height];
        for (int i =0 ;i< width ; i++)
        {
            level[i][0] = new Mur(resources,id,3,5, Mur.TypeDeMur.BOTTOM,i,0,widthEcran,heightEcran,width,height);
            level[i][height-1] = new Mur(resources,id,3,5, Mur.TypeDeMur.TOP,i,height-1,widthEcran,heightEcran,width,height);
        }

        for (int i =0 ;i< height-1 ; i++)
        {
            level[0][i] = new Mur(resources,id,3,5, Mur.TypeDeMur.RIGHT,0,i,widthEcran,heightEcran,width,height);
            level[width-1][i] = new Mur(resources,id,3,5, Mur.TypeDeMur.LEFT,width-1,i,widthEcran,heightEcran,width,height);
        }
        level[0][0] = new Mur(resources,id,3,5, Mur.TypeDeMur.BOTTOM_RIGHT_IN,0,0,widthEcran,heightEcran,width,height);
        level[0][height-1] = new Mur(resources,id,3,5, Mur.TypeDeMur.TOP_RIGHT_IN,0,height-1,widthEcran,heightEcran,width,height);
        level[width-1][0] = new Mur(resources,id,3,5, Mur.TypeDeMur.BOTTOM_LEFT_IN,width-1,0,widthEcran,heightEcran,width,height);
        level[width-1][height-1] = new Mur(resources,id,3,5, Mur.TypeDeMur.TOP_LEFT_IN,width-1,height-1,widthEcran,heightEcran,width,height);
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
