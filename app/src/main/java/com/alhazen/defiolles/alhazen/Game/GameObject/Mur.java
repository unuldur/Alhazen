package com.alhazen.defiolles.alhazen.Game.GameObject;

import android.content.res.Resources;

import com.alhazen.defiolles.alhazen.Game.Level.Level;

/**
 * Created by Julien Defiolles on 23/02/2016.
 */
public class Mur extends InanimateObject {


    @Override
    public void effetSurLevel(Level level) {

    }

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

    public Mur(int id, int nbFrameX,int nbFrameY,TypeDeMur typeDeMur) {
        super(id, nbFrameX, nbFrameY, 0, 0);
        this.typeDeMur = typeDeMur;
    }





    @Override
    public void initializeSprite(Resources resources) {
        super.initializeSprite(resources);
        initializeTexture(typeDeMur);
    }

    @Override
    public void initializeSprite(Resources resources, int posX, int posY, int tailleEcranX, int tailleEcranY, int tailleTableauX, int tailleTableauY, int tailleMurX, int tailleMurY) {
        initializeSprite(resources);
        this.tailleMurX = tailleMurX;
        this.tailleMurY = tailleMurY;
        decalageX =(tailleEcranX- getWidth()*(tailleTableauX)) / 2;
        decalageY = (tailleEcranY - getHeight()*(tailleTableauY))/2;
        setPosition(posX, posY);
        System.out.println(decalageX + " "+ decalageY+" "+ this.tailleMurX + " "+ this.tailleMurY);
    }

    @Override
    public void killMe(Level level) {

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
    public void updateFrame() {

    }


}
