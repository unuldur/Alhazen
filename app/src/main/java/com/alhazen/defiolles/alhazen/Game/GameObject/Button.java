package com.alhazen.defiolles.alhazen.Game.GameObject;

import android.content.res.Resources;

import com.alhazen.defiolles.alhazen.Game.Collisions;
import com.alhazen.defiolles.alhazen.Game.Level.Level;

/**
 * Created by PAYS on 08/03/2016.
 */
public class Button extends InanimateObject {

    boolean activer = false;
    boolean doitResterAppuyer;

    int posXElement;
    int posYElement;
    int posXLevel;
    int posYLevel;
    InanimateObject ina ;

    public Button(int id, int nbFrame, int posXElement, int posYElement , boolean doitResterAppuyer , InanimateObject inanimate) {
        super(id, nbFrame, 0,0);
        this.posXElement = posXElement;
        this.posYElement = posYElement;
        this.doitResterAppuyer = doitResterAppuyer;
        ina = inanimate;
    }

    @Override
    protected void aplieffectY(MoveObject moveObject) {

    }

    @Override
    public void initializeSprite(Resources resources, int posX, int posY, int tailleEcranX, int tailleEcranY, int tailleTableauX, int tailleTableauY,int tailleCaseX, int tailleCaseY) {
        posXLevel = posX;
        posYLevel = posY;
        super.initializeSprite(resources, posX, posY, tailleEcranX, tailleEcranY, tailleTableauX, tailleTableauY,tailleCaseX,tailleCaseY);
        setPosY(getPosY() + (tailleCaseY - getHeight()));
    }

    @Override
    public void killMe(Level level) {

    }

    @Override
    protected void aplieffectX(MoveObject moveObject) {

    }

    @Override
    public void updateFrame() {
        try {
            if (activer)
                spriteSheet.setFrame(1);
            else
                spriteSheet.setFrame(0);
        }catch (Exception e)
        {

        }
    }

    @Override
    public void effetSurLevel(Level level) {

        if(activer)
        {
            level.killThis(posXElement, posYElement);
        }
        else
        {
            level.addInanimateObject(posXElement,posYElement,ina);
        }

        if(level.toucheGameObject(this))
        {
            activer = true;
        }
        else
        {
            if(doitResterAppuyer) activer = false;
        }
    }
}
