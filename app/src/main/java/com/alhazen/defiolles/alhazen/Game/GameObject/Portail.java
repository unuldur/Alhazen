package com.alhazen.defiolles.alhazen.Game.GameObject;

import com.alhazen.defiolles.alhazen.Game.Collisions;
import com.alhazen.defiolles.alhazen.Game.Level.Level;

/**
 * Created by PAYS on 03/03/2016.
 */
public class Portail extends InanimateObject {

    private Portail tp;
    private boolean aTraverser;
    public Portail(int id, int nbFrame,Portail tp) {
        super(id, nbFrame, 0,0);

        this.tp = tp;
        this.tp.setTp(this);
    }

    public void setTp(Portail tp) {
        this.tp = tp;
    }

    public Portail(int id, int nbFrame) {
        super(id, nbFrame, 0,0);
        tp=null;
    }

    @Override
    protected void aplieffectX(MoveObject moveObject) {
        if(!moveObject.isaTraverser()) {
            moveObject.setPosition(tp.getPosX(), tp.getPosY());
            moveObject.setaTraverser(true);
        }
    }

    private void setaTraverser(boolean aTraverser) {
        this.aTraverser = aTraverser;
    }

    @Override
    protected void aplieffectY(MoveObject moveObject) {
    }

    @Override
    public void effectX(MoveObject moveObject) {
        if(Collisions.collisionGameObjects(this, moveObject)) {
            aplieffectX(moveObject);
        }
        else
        {
            if(!Collisions.collisionGameObjects(tp,moveObject))
                moveObject.setaTraverser(false);
        }
    }


    int frame = 0;
    @Override
    public void updateFrame() {
        try {
            spriteSheet.setFrame(frame);
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame++;
        if(frame == spriteSheet.getFrameCount())
            frame =0;
    }

    @Override
    public void effetSurLevel(Level level) {

    }

    @Override
    public void killMe(Level level) {

    }
}
