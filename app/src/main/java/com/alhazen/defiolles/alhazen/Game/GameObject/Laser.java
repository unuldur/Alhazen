package com.alhazen.defiolles.alhazen.Game.GameObject;

import android.content.res.Resources;

import com.alhazen.defiolles.alhazen.Game.Direction;
import com.alhazen.defiolles.alhazen.Game.Level;
import com.alhazen.defiolles.alhazen.Game.SpriteSheet;

/**
 * Created by PAYS on 03/03/2016.
 */
public class Laser extends InanimateObject {
    Direction.DirectionEnum direction;
    boolean touche = true;

    public void setTouche(boolean touche) {
        this.touche = touche;
    }

    public Laser(int id, int nbFrameX, int nbFrameY, int posX, int posY,Direction.DirectionEnum dir,SpriteSheet spriteSheet) {
        super(id, nbFrameX, nbFrameY, posX, posY);
        direction =dir;
        this.spriteSheet = spriteSheet;
    }

    @Override
    protected void aplieffectX(MoveObject moveObject) {
        if(moveObject.getClass() != BlocMouvant.class && touche)
            moveObject.meurt();
    }

    @Override
    protected void aplieffectY(MoveObject moveObject) {
        if(moveObject.getClass() != BlocMouvant.class && touche)
            moveObject.meurt();
    }



    int CurrentFrame = 0;
    @Override
    public void updateFrame() {
        switch (direction){

            case LEFT:
                CurrentFrame = (CurrentFrame+1)%2;
                break;
            case RIGHT:
                CurrentFrame = (CurrentFrame+1)%2;
                break;
            case TOP:
                CurrentFrame = (CurrentFrame+1)%2 +2;
                break;
            case BOTTOM:
                CurrentFrame = (CurrentFrame+1)%2 +2;
                break;
        }
        try {
            spriteSheet.setFrame(CurrentFrame,1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void effetSurLevel(Level level) {

    }
}
