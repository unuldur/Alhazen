package com.alhazen.defiolles.alhazen.Game.GameObject;

import android.content.res.Resources;

import com.alhazen.defiolles.alhazen.Game.Direction;

/**
 * Created by PAYS on 01/03/2016.
 */
public class Pic extends InanimateObject {

    Direction.DirectionEnum direction;
    public Pic(int id, int nbFrame,Direction.DirectionEnum direction) {
        super(id, nbFrame,0,0);
        this.direction = direction;
    }

    @Override
    protected void aplieffectX(MoveObject moveObject) {
        moveObject.meurt();
    }

    @Override
    protected void aplieffectY(MoveObject moveObject) {
        moveObject.meurt();
    }

    @Override
    public void updateFrame() {

    }

    @Override
    public void initializeSprite(Resources resources) {
        super.initializeSprite(resources);
        try {
            switch (direction) {

                case LEFT:
                    spriteSheet.setFrame(3);
                    break;
                case RIGHT:
                    spriteSheet.setFrame(1);
                    break;
                case TOP:
                    spriteSheet.setFrame(0);
                    break;
                case BOTTOM:
                    spriteSheet.setFrame(2);
                    break;
            }
        }catch (Exception e)
        {

        }
    }
}
