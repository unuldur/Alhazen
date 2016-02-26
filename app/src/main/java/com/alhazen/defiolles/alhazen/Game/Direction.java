package com.alhazen.defiolles.alhazen.Game;

import android.view.Surface;

/**
 * Created by PAYS on 21/02/2016.
 */
public class Direction {
    public enum DirectionEnum{LEFT,CENTER,RIGHT,TOP,BOTTOM}


    public static int getIntDirection(DirectionEnum d)
    {
        switch (d)
        {
            case TOP:
                return -1;
            case BOTTOM:
                return 1;
            case LEFT:
                return -1;
            case RIGHT:
                return 1;
        }
        return 0;
    }

    public static DirectionEnum getDirectionToRotation(DirectionEnum dir,int rotation)
    {
        switch (rotation)
        {
            case Surface.ROTATION_90:
                switch (dir){

                    case LEFT:
                        return DirectionEnum.BOTTOM;
                    case RIGHT:
                        return DirectionEnum.TOP;
                    case TOP:
                        return DirectionEnum.LEFT;
                    case BOTTOM:
                        return DirectionEnum.RIGHT;
                }
                break;
            case Surface.ROTATION_180:
                switch (dir){
                    case TOP:
                        return DirectionEnum.BOTTOM;
                    case BOTTOM:
                        return DirectionEnum.TOP;
                }
                break;
                case Surface.ROTATION_270:
                    switch (dir){

                        case LEFT:
                            return DirectionEnum.TOP;
                        case RIGHT:
                            return DirectionEnum.BOTTOM;
                        case TOP:
                            return DirectionEnum.RIGHT;
                        case BOTTOM:
                            return DirectionEnum.LEFT;
                }
                break;
        }
        return dir;
    }
}
