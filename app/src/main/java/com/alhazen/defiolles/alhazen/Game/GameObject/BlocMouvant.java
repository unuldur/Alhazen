package com.alhazen.defiolles.alhazen.Game.GameObject;

import android.view.Surface;

import com.alhazen.defiolles.alhazen.Game.Collisions;
import com.alhazen.defiolles.alhazen.Game.Direction;
import com.alhazen.defiolles.alhazen.Game.Level;

/**
 * Created by PAYS on 01/03/2016.
 */
public class BlocMouvant extends MoveObject {
    public BlocMouvant(int id, int nbFrame, int posX, int posY) {
        super(id, nbFrame, posX, posY);
    }

    @Override
    public void whenTuchOtherMoveObject(MoveObject o,Level l) {
        if(!Collisions.collisionGameObjects(this, o)){
            setDirectionX(Direction.DirectionEnum.CENTER);
            return;
        }
        boolean dessus,dessous;
        if (o.getDirectionX() != Direction.DirectionEnum.CENTER) {

            setDirectionX(o.getDirectionX());
        }

        if(getOrientation() == Surface.ROTATION_180 || getOrientation() == Surface.ROTATION_0) {
            dessus = Collisions.gameObjectDessusY(o,this);
            dessous = Collisions.gameObjectDessousY(o, this);
            if ((!dessus || getDirectionY() != Direction.DirectionEnum.BOTTOM) && (!dessous || getDirectionY() != Direction.DirectionEnum.TOP)) {
                effectX(o);
            } else {
                setDirectionX(Direction.DirectionEnum.CENTER);
            }
            effectY(o);

        }
        else {
            dessous = Collisions.gameObjectDessusX(o, this);
            dessus = Collisions.gameObjectDessousX(o, this);
            if ((!dessus || getDirectionY() != Direction.DirectionEnum.BOTTOM) && (!dessous || getDirectionY() != Direction.DirectionEnum.TOP)) {
                effectY(o);
            } else {
                setDirectionX(Direction.DirectionEnum.CENTER);
            }
            effectX(o);

        }
        if(dessus && getDirectionY()== Direction.DirectionEnum.TOP) o.meurt();
        if(dessous && getDirectionY()== Direction.DirectionEnum.BOTTOM) o.meurt();




    }

    @Override
    public void updateFrame() {

    }

}
