package com.alhazen.defiolles.alhazen;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

/**
 * Created by PAYS on 21/02/2016.
 */
public abstract class GameObject {
    protected SpriteSheet spriteSheet;
    private RectF mPosition;
    private int posX;
    private int posY;

    public GameObject(Resources resources, int id, int nbFrame, int posX, int posY) {
        this.spriteSheet = new SpriteSheet(resources,id,nbFrame);
        this.posX = posX;
        this.posY = posY;
        mPosition = new RectF();
        setRec();
    }

    public GameObject(Resources resources, int id, int nbFrameX,int nbFrameY, int posX, int posY) {
        this.spriteSheet = new SpriteSheet(resources,id,nbFrameX,nbFrameY);
        this.posX = posX;
        this.posY = posY;
        mPosition = new RectF();
        setRec();
    }

    public abstract void updateFrame();

    public void setPosition(int x, int y)
    {
        posX = x;
        posY = y;
        setRec();
    }

    public void setPosX(int x)
    {
        posX = x;
        setRec();
    }

    public int getPosX() {
        return posX;
    }

    public void setPosY(int y)
    {
        posY = y;
        setRec();
    }

    public int getPosY() {
        return posY;
    }

    private void setRec()
    {
        mPosition.set(posX, posY, posX + spriteSheet.getWidth(),posY+ spriteSheet.getHeight());
    }

    public void draw(Canvas canvas, Paint paint)
    {
        spriteSheet.drawCurrentFrame(canvas, mPosition, paint);
    }

    public int getWidth()
    {
        return spriteSheet.getWidth();
    }

    public int getHeight()
    {
        return spriteSheet.getHeight();
    }
}
