package com.alhazen.defiolles.alhazen.Game.GameObject;

import android.content.res.Resources;

import com.alhazen.defiolles.alhazen.Game.Level.Level;

/**
 * Created by PAYS on 23/02/2016.
 */
public abstract class InanimateObject extends GameObject {


    protected int decalageX;
    protected int decalageY;
    protected int tailleMurX;
    protected int tailleMurY;
    public InanimateObject(int id, int nbFrame, int posX, int posY) {
        super(id, nbFrame, posX, posY);
    }

    public InanimateObject(int id, int nbFrameX, int nbFrameY, int posX, int posY) {
        super(id, nbFrameX, nbFrameY, posX, posY);
    }


    public void initializeSprite(Resources resources,int posX,int posY,
                                 int tailleEcranX,int tailleEcranY,int tailleTableauX,int tailleTableauY
                                    ,int tailleMurX,int tailleMurY) {
        initializeSprite(resources);
        this.tailleMurX = tailleMurX;
        this.tailleMurY = tailleMurY;
        decalageX =(tailleEcranX- tailleMurX*(tailleTableauX)) / 2;
        decalageY = (tailleEcranY - tailleMurY*(tailleTableauY))/2;
        setPosition(posX, posY);
    }

    @Override
    public void setPosition(int posX,int posY)
    {
        super.setPosition(posX * tailleMurX + getDecalageX(), posY * tailleMurY +getDecalageY());
    }
    public abstract void killMe(Level level);



    public int getDecalageX() {
        return decalageX;
    }

    public int getDecalageY() {
        return decalageY;
    }


}
