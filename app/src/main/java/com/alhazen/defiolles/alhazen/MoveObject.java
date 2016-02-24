package com.alhazen.defiolles.alhazen;

import android.content.res.Resources;

/**
 * Created by PAYS on 22/02/2016.
 */
public abstract class MoveObject extends GameObject {


    protected Direction.DirectionEnum directionX;
    protected Direction.DirectionEnum directionY;
    private boolean auSol;

    public MoveObject(Resources resources, int id, int nbFrame,int posX,int posY) {
        super(resources, id, nbFrame,posX,posY);
        directionX = Direction.DirectionEnum.CENTER;
        directionY = Direction.DirectionEnum.BOTTOM;
        auSol = true;
    }

    public void move(int pas,Level level)
    {
        setPosY(getPosY() + Direction.getIntDirectionY(directionY)*pas*2);
        level.avanceY(this);
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
}
