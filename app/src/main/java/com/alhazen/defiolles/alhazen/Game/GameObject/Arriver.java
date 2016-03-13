package com.alhazen.defiolles.alhazen.Game.GameObject;

import android.content.res.Resources;

import com.alhazen.defiolles.alhazen.Game.Level.Level;

/**
 * Created by PAYS on 05/03/2016.
 */
public class Arriver extends InanimateObject {
    public Arriver(int id, int nbFrame) {
        super(id, nbFrame, 0,0);
    }


    @Override
    protected void aplieffectX(MoveObject moveObject) {
    }

    @Override
    protected void aplieffectY(MoveObject moveObject) {
    }

    @Override
    public void effetSurLevel(Level level) {
        if(level.toucheGameObject(this,Player.class))
            level.setLevelFinish(true);
    }

    @Override
    public void updateFrame() {

    }

    @Override
    public void killMe(Level level) {

    }

    int posX;
    int posY;
    @Override
    public void initializeSprite(Resources resources, int posX, int posY, int tailleEcranX, int tailleEcranY, int tailleTableauX, int tailleTableauY, int tailleMurX, int tailleMurY) {
        this.posX = posX;
        this.posY = posY;
        super.initializeSprite(resources, posX, posY, tailleEcranX, tailleEcranY, tailleTableauX, tailleTableauY, tailleMurX, tailleMurY);
    }
}
