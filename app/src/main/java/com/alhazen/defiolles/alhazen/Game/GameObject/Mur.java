package com.alhazen.defiolles.alhazen.Game.GameObject;

import android.content.res.Resources;
import android.view.Surface;

import com.alhazen.defiolles.alhazen.Game.Direction;
import com.alhazen.defiolles.alhazen.Game.GameObject.InanimateObject;
import com.alhazen.defiolles.alhazen.Game.GameObject.MoveObject;

/**
 * Created by Julien Defiolles on 23/02/2016.
 */
public class Mur extends InanimateObject {

    public enum TypeDeMur{
        LEFT,
        CENTER,
        RIGHT,
        TOP,
        BOTTOM,
        TOP_RIGHT,
        TOP_LEFT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        TOP_RIGHT_IN,
        TOP_LEFT_IN,
        BOTTOM_LEFT_IN,
        BOTTOM_RIGHT_IN,
    }

    private TypeDeMur typeDeMur;

    public Mur(Resources resources, int id, int nbFrameX,int nbFrameY,TypeDeMur typeDeMur,int posX,int posY,
               int tailleEcranX,int tailleEcranY,int tailleTableauX,int tailleTableauY) {
        super(resources, id, nbFrameX, nbFrameY, 0, 0);
        this.typeDeMur = typeDeMur;
        initializeTexture(typeDeMur);
        int decalageX =(tailleEcranX- getWidth()*(tailleTableauX))/2;
        int decalageY =(tailleEcranY- getHeight()*(tailleTableauY))/2;
        setPosition(posX*getWidth()+decalageX,posY*getHeight()+decalageY);
    }

    private void initializeTexture(TypeDeMur typeDeMur)
    {
        try {
            switch (typeDeMur) {

                case LEFT:
                    spriteSheet.setFrame(0, 1);
                    break;
                case CENTER:
                    spriteSheet.setFrame(1, 1);
                    break;
                case RIGHT:
                    spriteSheet.setFrame(2, 1);
                    break;
                case TOP:
                    spriteSheet.setFrame(1, 0);
                    break;
                case BOTTOM:
                    spriteSheet.setFrame(1, 2);
                    break;
                case TOP_RIGHT:
                    spriteSheet.setFrame(2, 0);
                    break;
                case TOP_LEFT:
                    spriteSheet.setFrame(0, 0);
                    break;
                case BOTTOM_LEFT:
                    spriteSheet.setFrame(0, 2);
                    break;
                case BOTTOM_RIGHT:
                    spriteSheet.setFrame(2, 2);
                    break;
                case TOP_RIGHT_IN:
                    spriteSheet.setFrame(0, 4);
                    break;
                case TOP_LEFT_IN:
                    spriteSheet.setFrame(1, 4);
                    break;
                case BOTTOM_LEFT_IN:
                    spriteSheet.setFrame(1, 3);
                    break;
                case BOTTOM_RIGHT_IN:
                    spriteSheet.setFrame(0, 3);
                    break;
            }
        }catch (Exception e)
        {
            
        }
    }

    @Override
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
        if(x != 0 ) moveObject.setPosX(moveObject.getPosX() - x);
        effectX(moveObject);
    }

    @Override
    protected void aplieffectY(MoveObject moveObject) {

        int y =0;
        switch (moveObject.getOrientation())
        {
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
                y = -Direction.getIntDirection(moveObject.getDirectionY());
                moveObject.setAuSol(true);
                break;
        }
        if(y != 0 ) moveObject.setPosY(moveObject.getPosY() - y);
        effectY(moveObject);
    }

    @Override
    public void updateFrame() {

    }


}
