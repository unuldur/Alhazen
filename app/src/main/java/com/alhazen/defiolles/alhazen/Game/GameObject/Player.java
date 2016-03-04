package com.alhazen.defiolles.alhazen.Game.GameObject;

import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import com.alhazen.defiolles.alhazen.Game.Direction;
import com.alhazen.defiolles.alhazen.Game.Level;

/**
 * Created by PAYS on 21/02/2016.
 */
public class Player extends MoveObject {

    private int currentFrame;
    private int nbFrameMouvement;
    private long lastFrameChangeTime = 0;
    private long frameLengthInMilliseconds;

    public Player(int id, int nbFrame , int nbFrameMouvement, long frameLengthInMilliseconds,int posX,int posY) {
        super(id, nbFrame,posX,posY);
        this.nbFrameMouvement = nbFrameMouvement;
        this.frameLengthInMilliseconds = frameLengthInMilliseconds;
    }

    @Override
        public void updateFrame() {
        try {
            long time  = System.currentTimeMillis();
                if ( time > lastFrameChangeTime + frameLengthInMilliseconds) {
                    lastFrameChangeTime = time;
                    switch (directionX) {

                        case LEFT:
                            if (currentFrame <= nbFrameMouvement)
                                currentFrame = (currentFrame + 1) % nbFrameMouvement;
                            else
                                currentFrame = 0;
                            break;
                        case CENTER:
                            currentFrame = (spriteSheet.getFrameCount() - 1) / 2;
                            break;
                        case RIGHT:
                            if (currentFrame > nbFrameMouvement + 1)
                                currentFrame = (nbFrameMouvement + 1) + ((currentFrame - (nbFrameMouvement  )) % nbFrameMouvement);
                            else
                                currentFrame = nbFrameMouvement + 2;
                            break;
                    }
                    spriteSheet.setFrame(currentFrame);
                }
        } catch (Exception e) {
        e.printStackTrace();
        }
    }

    @Override
    protected void aplieffectX(MoveObject moveObject) {

    }

    @Override
    protected void aplieffectY(MoveObject moveObject) {

    }


    @Override
    public void whenTouchOtherMoveObject(MoveObject o, Level l) {

    }
}
