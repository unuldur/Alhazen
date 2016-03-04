package com.alhazen.defiolles.alhazen.Game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.Display;

import java.io.Serializable;

/**
 * Created by PAYS on 21/02/2016.
 */
public class SpriteSheet implements Serializable{

    private int frameWidth;
    private int frameHeight;
    private int frameCountX;
    private int frameCountY;

    Bitmap spriteSheet;
    Bitmap imageToDraw;
    Resources resources;
    Matrix matrixRotation;

    private int id;



    public SpriteSheet(Resources resources,int id,int frameCount) {
        this.frameCountX = frameCount;
        this.resources = resources;
        this.id = id;
        frameCountY = 1;
        spriteSheet = BitmapFactory.decodeResource(resources, id);
        frameWidth = spriteSheet.getWidth()/frameCount;
        frameHeight = spriteSheet.getHeight();
        spriteSheet = Bitmap.createScaledBitmap(spriteSheet,
                frameWidth * frameCount,
                frameHeight,
                false);
        matrixRotation = new Matrix();
        imageToDraw = Bitmap.createBitmap(spriteSheet,0,0,frameWidth,frameHeight);

    }

    public SpriteSheet(Resources resources,int id,int frameCountX, int frameCountY)
    {
        this.resources = resources;
        this.frameCountX = frameCountX;
        this.frameCountY = frameCountY;
        this.id = id;
        spriteSheet = BitmapFactory.decodeResource(resources, id);
        frameWidth = spriteSheet.getWidth()/frameCountX;
        frameHeight = spriteSheet.getHeight()/frameCountY;
        spriteSheet = Bitmap.createScaledBitmap(spriteSheet,
                frameWidth * frameCountX,
                frameHeight * frameCountY,
                false);

        matrixRotation = new Matrix();
        imageToDraw = Bitmap.createBitmap(spriteSheet,0,0,frameWidth,frameHeight);
    }

    public SpriteSheet clone()
    {
        return new SpriteSheet(resources,id,frameCountX,frameCountY);
    }

    public void setFrame(int frame) throws Exception {
        if(frame > frameCountX)  throw new Exception();
        imageToDraw = Bitmap.createBitmap(spriteSheet,frame * frameWidth,0,frameWidth,frameHeight,matrixRotation,true);
    }

    public void setFrame(int frameX,int frameY) throws Exception {
        if(frameX > frameCountX && frameY > frameCountY)  throw new Exception();
        imageToDraw = Bitmap.createBitmap(spriteSheet,frameX * frameWidth,frameY * frameHeight,frameWidth,frameHeight,matrixRotation,true);
    }

    public void drawCurrentFrame(Canvas canvas,int posX,int posY,Paint paint)
    {
        canvas.drawBitmap(imageToDraw,posX,posY,paint);
    }

    public void rotation(int degree)
    {
        matrixRotation.postRotate(degree);
    }

    public void setRotation(int degree)
    {
        matrixRotation.setRotate(degree);
    }

    public int getWidth() {
        return frameWidth;
    }

    public int getHeight() {
        return frameHeight;
    }

    public int getFrameCount() {
        return frameCountX;
    }
}
