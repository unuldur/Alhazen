package com.alhazen.defiolles.alhazen.Game.GameObject;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.Surface;

import com.alhazen.defiolles.alhazen.Game.Collisions;
import com.alhazen.defiolles.alhazen.Game.Direction;
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


    public void effectX(MoveObject moveObject)
    {
        if(Collisions.collisionGameObjects(this, moveObject)) aplieffectX(moveObject);
    }

    public void effectY(MoveObject moveObject)
    {
        if(Collisions.collisionGameObjects(this,moveObject)) aplieffectY(moveObject);
    }

    protected void aplieffectX(MoveObject moveObject) {
        int x =0;
        switch (moveObject.getOrientation())
        {
            case Surface.ROTATION_0:
                x = Direction.getIntDirection(moveObject.getDirectionX());
                break;
            case Surface.ROTATION_90:
                x = -Direction.getIntDirection(moveObject.getDirectionY());
                moveObject.setAuSol(true);
                break;
            case Surface.ROTATION_270:
                x = -Direction.getIntDirection(moveObject.getDirectionY());
                moveObject.setAuSol(true);
                break;
            case Surface.ROTATION_180:
                x = -Direction.getIntDirection(moveObject.getDirectionX());
                break;
        }
        if(x != 0 ){
            moveObject.setPosX(moveObject.getPosX() - x);
            effectX(moveObject);
        }
    }

    protected void aplieffectY(MoveObject moveObject) {
        int y = 0;
        switch (moveObject.getOrientation()) {
            case Surface.ROTATION_0:
                y = Direction.getIntDirection(moveObject.getDirectionY());
                moveObject.setAuSol(true);
                break;
            case Surface.ROTATION_90:
                y = Direction.getIntDirection(moveObject.getDirectionX());
                break;
            case Surface.ROTATION_270:
                y = -Direction.getIntDirection(moveObject.getDirectionX());
                break;
            case Surface.ROTATION_180:
                y = Direction.getIntDirection(moveObject.getDirectionY());
                moveObject.setAuSol(true);
                break;
        }
        if (y != 0) {
            moveObject.setPosY(moveObject.getPosY() - y);
            effectY(moveObject);
        }
    }
}
