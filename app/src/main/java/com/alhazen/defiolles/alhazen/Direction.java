package com.alhazen.defiolles.alhazen;

/**
 * Created by PAYS on 21/02/2016.
 */
public class Direction {
    public enum DirectionEnum{LEFT,CENTER,RIGHT,TOP,BOTTOM}

    public static int getIntDirectionX(DirectionEnum d)
    {
        switch (d)
        {
            case LEFT:
                return -1;
            case RIGHT:
                return 1;
        }
        return 0;
    }

    public static int getIntDirectionY(DirectionEnum d)
    {
        switch (d)
        {
            case TOP:
                return -1;
            case BOTTOM:
                return 1;
        }
        return 0;
    }
}
