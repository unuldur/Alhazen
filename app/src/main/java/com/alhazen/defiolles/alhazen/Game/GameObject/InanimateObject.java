package com.alhazen.defiolles.alhazen.Game.GameObject;

import android.content.res.Resources;

import com.alhazen.defiolles.alhazen.Game.Collisions;

/**
 * Created by PAYS on 23/02/2016.
 */
public abstract class InanimateObject extends GameObject {


    private int decalageX;
    private int decalageY;

    public InanimateObject(int id, int nbFrame, int posX, int posY) {
        super(id, nbFrame, posX, posY);
    }

    public InanimateObject(int id, int nbFrameX, int nbFrameY, int posX, int posY) {
        super(id, nbFrameX, nbFrameY, posX, posY);
    }


    public void initializeSprite(Resources resources,int posX,int posY,
                                 int tailleEcranX,int tailleEcranY,int tailleTableauX,int tailleTableauY) {
        initializeSprite(resources);
        decalageX =(tailleEcranX- getWidth()*(tailleTableauX)) / 2;
        decalageY = (tailleEcranY - getHeight()*(tailleTableauY))/2;
        setPosition(posX, posY);
    }

    public int getDecalageX() {
        return decalageX;
    }

    public int getDecalageY() {
        return decalageY;
    }

    @Override
    public void setPosition(int posX,int posY)
    {
        super.setPosition(posX * getWidth() + getDecalageX(), posY * getHeight() +getDecalageY());
    }
}
