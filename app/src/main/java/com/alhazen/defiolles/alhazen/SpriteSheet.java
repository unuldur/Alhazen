package com.alhazen.defiolles.alhazen;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by PAYS on 21/02/2016.
 */
public class SpriteSheet {
    private Rect frameToDraw;
    private int frameWidth;
    private int frameHeight;
    private int frameCountX;
    private int frameCountY;

    Bitmap spriteSheet;

    public SpriteSheet(Resources resources,int id,int frameCount) {
        this.frameCountX = frameCount;
        frameCountY = 1;
        spriteSheet = BitmapFactory.decodeResource(resources, id);
        frameWidth = spriteSheet.getWidth()/frameCount;
        frameHeight = spriteSheet.getHeight();
        spriteSheet = Bitmap.createScaledBitmap(spriteSheet,
                frameWidth * frameCount,
                frameHeight,
                false);

        frameToDraw= new Rect(0,0,frameWidth,frameHeight);
    }

    public SpriteSheet(Resources resources,int id,int frameCountX, int frameCountY)
    {
        this.frameCountX = frameCountX;
        this.frameCountY = frameCountY;
        spriteSheet = BitmapFactory.decodeResource(resources, id);
        frameWidth = spriteSheet.getWidth()/frameCountX;
        frameHeight = spriteSheet.getHeight()/frameCountY;
        spriteSheet = Bitmap.createScaledBitmap(spriteSheet,
                frameWidth * frameCountX,
                frameHeight * frameCountY,
                false);
        frameToDraw= new Rect(0,0,frameWidth,frameHeight);
    }

    public void setFrame(int frame) throws Exception {
        if(frame > frameCountX)  throw new Exception();
        frameToDraw.left = frame * frameWidth;
        frameToDraw.right = frameToDraw.left + frameWidth;
    }

    public void setFrame(int frameX,int frameY) throws Exception {
        if(frameX > frameCountX && frameY > frameCountY)  throw new Exception();
        frameToDraw.left = frameX * frameWidth;
        frameToDraw.top = frameY * frameHeight;
        frameToDraw.bottom = frameToDraw.top + frameHeight;
        frameToDraw.right = frameToDraw.left + frameWidth;
    }

    public void drawCurrentFrame(Canvas canvas,RectF dest,Paint paint)
    {
        canvas.drawBitmap(spriteSheet,frameToDraw,dest,paint);
    }

    public void rotation(int degree)
    {
        Matrix m = new Matrix();
        m.postRotate(degree);
        spriteSheet = Bitmap.createBitmap(spriteSheet, 0, 0,
                spriteSheet.getWidth(), spriteSheet.getHeight(),
                m, true);
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
