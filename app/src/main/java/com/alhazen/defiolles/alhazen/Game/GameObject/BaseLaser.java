package com.alhazen.defiolles.alhazen.Game.GameObject;

import android.content.res.Resources;

import com.alhazen.defiolles.alhazen.Game.Direction;
import com.alhazen.defiolles.alhazen.Game.Level;

/**
 * Created by PAYS on 03/03/2016.
 */
public class BaseLaser extends InanimateObject {
    Direction.DirectionEnum direction;
    private int posXLevel;
    private int posYLevel;
    public BaseLaser(int id, int nbFrameX, int nbFrameY, int posX, int posY,Direction.DirectionEnum dir) {
        super(id, nbFrameX, nbFrameY, posX, posY);
        direction =dir;
    }

    @Override
    protected void aplieffectX(MoveObject moveObject) {

        if(moveObject.getClass() != BlocMouvant.class)
            moveObject.meurt();
    }

    @Override
    protected void aplieffectY(MoveObject moveObject) {
        if(moveObject.getClass() != BlocMouvant.class)
            moveObject.meurt();
    }

    int CurrentFrame = 0;
    @Override
    public void updateFrame() {
        switch (direction){

            case LEFT:
                CurrentFrame = (CurrentFrame+1)%2+4;
                break;
            case RIGHT:
                CurrentFrame = (CurrentFrame+1)%2;
                break;
            case TOP:
                CurrentFrame = (CurrentFrame+1)%2 +2+6;
                break;
            case BOTTOM:
                CurrentFrame = (CurrentFrame+1)%2 +2;
                break;
        }
        try {
            spriteSheet.setFrame(CurrentFrame,0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initializeSprite(Resources resources, int posX, int posY, int tailleEcranX, int tailleEcranY, int tailleTableauX, int tailleTableauY) {
        posXLevel = posX;
        posYLevel = posY;
        super.initializeSprite(resources, posX, posY, tailleEcranX, tailleEcranY, tailleTableauX, tailleTableauY);
    }

    @Override
    public void effetSurLevel(Level level) {
        int tmpX = posXLevel;
        int tmpY = posYLevel;
        int incrementX = 0;
        int incrementY = 0;
        switch (direction)
        {

            case LEFT:
                incrementX = -1;
                break;
            case RIGHT:
                incrementX = 1;
                break;
            case TOP:
                incrementY = -1;
                break;
            case BOTTOM:
                incrementY = 1;
                break;
        }
        tmpX += incrementX;
        tmpY += incrementY;
        InanimateObject in = level.exist(tmpX,tmpY);
        boolean aEffacer = false;
        while (in == null || in.getClass() == Laser.class)
        {

            if(in == null) {
                if(!aEffacer) {
                    Laser laser = new Laser(spriteId, nbFrameX, nbFrameY, tmpX, tmpY, direction, spriteSheet.clone());
                    laser.decalageX = decalageX;
                    laser.decalageY = decalageY;
                    laser.setPosition(tmpX, tmpY);
                    level.addInanimateObject(tmpX, tmpY, laser);
                }
            }
            else
            {

                if(aEffacer) {
                    level.addInanimateObject(tmpX, tmpY, null);
                }
            }
            if(level.toucheGameObject(tmpX,tmpY)) {
                aEffacer = true;
                if(in != null)
                    ((Laser)in).setTouche(false);
            }
            tmpX += incrementX;
            tmpY += incrementY;
            in = level.exist(tmpX,tmpY);
        }
    }
}
