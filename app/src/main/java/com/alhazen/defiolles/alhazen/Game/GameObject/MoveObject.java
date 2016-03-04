package com.alhazen.defiolles.alhazen.Game.GameObject;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.Surface;

import com.alhazen.defiolles.alhazen.Game.Collisions;
import com.alhazen.defiolles.alhazen.Game.Direction;
import com.alhazen.defiolles.alhazen.Game.Level;

/**
 * Created by PAYS on 22/02/2016.
 */
public abstract class MoveObject extends GameObject{


    protected Direction.DirectionEnum directionX;
    protected Direction.DirectionEnum directionY;
    private boolean auSol;
    private int orientation;
    private int posXDepart;
    private int posYDepart;
    protected boolean aToucher = false;
    boolean aTraverser = false;

    public boolean isaTraverser() {
        return aTraverser;
    }

    public void setaTraverser(boolean aTraverser) {
        this.aTraverser = aTraverser;
    }

    public void setaToucher(boolean aToucher) {
        this.aToucher = aToucher;
    }

    public MoveObject( int id, int nbFrame,int posX,int posY) {
        super(id, nbFrame,posX,posY);
        directionX = Direction.DirectionEnum.CENTER;
        directionY = Direction.DirectionEnum.BOTTOM;
        auSol = true;
        posXDepart = posX;
        posYDepart = posY;
    }

    public void move(int pas,Level level)
    {
        switch (orientation)
        {
            case Surface.ROTATION_0:
                setPosX(getPosX() + Direction.getIntDirection(getDirectionX()) * pas);
                level.avanceX(this);
                setPosY(getPosY() + Direction.getIntDirection(getDirectionY()) * pas * 2);
                level.avanceY(this);
                break;
            case Surface.ROTATION_90:
                setPosX(getPosX() - Direction.getIntDirection(getDirectionY()) * pas * 2);
                level.avanceX(this);
                setPosY(getPosY() + Direction.getIntDirection(getDirectionX()) * pas);
                level.avanceY(this);
                break;
            case Surface.ROTATION_270:
                setPosX(getPosX() - Direction.getIntDirection(getDirectionY()) * pas * 2);
                level.avanceX(this);
                setPosY(getPosY() - Direction.getIntDirection(getDirectionX()) * pas);
                level.avanceY(this);
                break;
            case Surface.ROTATION_180:
                setPosX(getPosX() - Direction.getIntDirection(getDirectionX()) * pas);
                level.avanceX(this);
                setPosY(getPosY() + Direction.getIntDirection(getDirectionY()) * pas * 2);
                level.avanceY(this);
                break;
        }
    }

    @Override
    public void effectX(MoveObject moveObject) {

        super.effectX(moveObject);
    }

    @Override
    public void effectY(MoveObject moveObject) {
        super.effectY(moveObject);
    }

    public Direction.DirectionEnum getDirectionX() {
        return directionX;
    }
    public void setDirectionX(Direction.DirectionEnum direction) {
        this.directionX = direction;

    }

    public Direction.DirectionEnum getDirectionY() {
        return directionY;
    }

    public void setDirectionY(Direction.DirectionEnum directionY) {

        this.directionY = directionY;
    }

    public boolean isAuSol() {
        return auSol;
    }

    public void setAuSol(boolean auSol) {
        this.auSol = auSol;
    }

    public void turnThis()
    {
        spriteSheet.rotation(180);
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
        switch (orientation)
        {
            case Surface.ROTATION_0:
                spriteSheet.setRotation(0);
                break;
            case Surface.ROTATION_90:
                spriteSheet.setRotation(90);
                break;
            case Surface.ROTATION_180:
                spriteSheet.setRotation(180);
                break;
            case Surface.ROTATION_270:
                spriteSheet.setRotation(90);
                break;
        }
        if(getDirectionY()== Direction.DirectionEnum.TOP && orientation != Surface.ROTATION_180) turnThis();
    }

    public int getOrientation() {
        return orientation;

    }

    public void meurt() {
        setPosX(posXDepart);
        setPosY(posYDepart);
        setAuSol(true);
    }

    public abstract void whenTouchOtherMoveObject(MoveObject o, Level l);
}
