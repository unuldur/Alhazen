package com.alhazen.defiolles.alhazen.Game.GameObject;

import android.content.res.Resources;

import com.alhazen.defiolles.alhazen.Game.Collisions;

/**
 * Created by PAYS on 23/02/2016.
 */
public abstract class InanimateObject extends GameObject {
    public InanimateObject(int id, int nbFrame, int posX, int posY) {
        super(id, nbFrame, posX, posY);
    }

    public InanimateObject(int id, int nbFrameX, int nbFrameY, int posX, int posY) {
        super(id, nbFrameX, nbFrameY, posX, posY);
    }

    protected abstract void aplieffectX(MoveObject moveObject);

    public void effectX(MoveObject moveObject)
    {
        if(Collisions.collisionGameObjects(this, moveObject)) aplieffectX(moveObject);
    }

    protected abstract void aplieffectY(MoveObject moveObject);

    public void effectY(MoveObject moveObject)
    {
        if(Collisions.collisionGameObjects(this,moveObject)) aplieffectY(moveObject);
    }
}
