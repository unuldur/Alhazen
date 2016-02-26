package com.alhazen.defiolles.alhazen.Game.GameObject;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.alhazen.defiolles.alhazen.Game.SpriteSheet;

import java.io.Serializable;

/**
 * Created by PAYS on 21/02/2016.
 */
public abstract class GameObject implements Serializable{
    protected transient SpriteSheet spriteSheet;
    private int posX;
    private int posY;
    private int spriteId;
    private int nbFrameX;
    private int nbFrameY;

    public GameObject(int id, int nbFrame, int posX, int posY) {
        spriteId = id;
        this.nbFrameX = nbFrame;
        nbFrameY = 0;
        this.posX = posX;
        this.posY = posY;
    }

    public GameObject( int id, int nbFrameX,int nbFrameY, int posX, int posY) {
        spriteId = id;
        this.nbFrameX = nbFrameX;
        this.nbFrameY = nbFrameY;
        this.posX = posX;
        this.posY = posY;
    }

    public void initializeSprite(Resources resources)
    {
        if(nbFrameY != 0)
            spriteSheet = new SpriteSheet(resources,spriteId,nbFrameX,nbFrameY);
        else
            spriteSheet = new SpriteSheet(resources,spriteId,nbFrameX);
    }

    public abstract void updateFrame();

    public void setPosition(int x, int y)
    {
        posX = x;
        posY = y;
    }

    public void setPosX(int x)
    {
        posX = x;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosY(int y)
    {
        posY = y;
    }

    public int getPosY() {
        return posY;
    }


    public void draw(Canvas canvas, Paint paint)
    {
        spriteSheet.drawCurrentFrame(canvas, posX,posY, paint);
    }

    public int getWidth()
    {
        return spriteSheet.getWidth();
    }

    public int getHeight()
    {
        return spriteSheet.getHeight();
    }

    public void invertXY()
    {
        int tmp = posX;
        posX = posY;
        posY = tmp;
    }
}
