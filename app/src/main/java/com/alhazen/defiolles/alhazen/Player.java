package com.alhazen.defiolles.alhazen;

import android.content.res.Resources;
import android.util.Log;

/**
 * Created by PAYS on 21/02/2016.
 */
public class Player extends MoveObject  {

    private int currentFrame;
    private int nbFrameMouvement;
    private long lastFrameChangeTime = 0;
    private long frameLengthInMilliseconds;

    public Player(Resources resources, int id, int nbFrame , int nbFrameMouvement, long frameLengthInMilliseconds,int posX,int posY) {
        super(resources, id, nbFrame,posX,posY);
        this.nbFrameMouvement = nbFrameMouvement;
        this.frameLengthInMilliseconds = frameLengthInMilliseconds;
        Log.d("format",getHeight()+" "+getWidth());
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
    public void move(int pas,Level level){
        setPosX(getPosX()+Direction.getIntDirectionX(directionX)*pas);
        level.avanceX(this);
        super.move(pas,level);
    }


}
