package com.alhazen.defiolles.alhazen.Game;

import android.graphics.Rect;
import android.graphics.RectF;

import com.alhazen.defiolles.alhazen.Game.GameObject.GameObject;

/**
 * Created by PAYS on 23/02/2016.
 */
public class Collisions {
    public static boolean collisionGameObjects(GameObject gameObject1, GameObject gameObject2)
    {
        return !((gameObject2.getPosX() >= gameObject1.getPosX() + gameObject1.getWidth())
                || (gameObject2.getPosX() + gameObject2.getWidth() <= gameObject1.getPosX())
                || (gameObject2.getPosY() >= gameObject1.getPosY() + gameObject1.getHeight())
                || (gameObject2.getPosY() + gameObject2.getHeight() <= gameObject1.getPosY()));
    }

    public static boolean gameObjectDessusY(GameObject gameObject1, GameObject gameObject2)
    {
        return !((gameObject2.getPosX() >= gameObject1.getPosX() + gameObject1.getWidth())
                || (gameObject2.getPosX() + gameObject2.getWidth() <= gameObject1.getPosX())
                || (0 >= gameObject1.getPosY() + gameObject1.getHeight())
                || (gameObject2.getPosY() <= gameObject1.getPosY()));
    }

    public static boolean gameObjectDessousY(GameObject gameObject1, GameObject gameObject2)
    {
        return !((gameObject2.getPosX() >= gameObject1.getPosX() + gameObject1.getWidth())
                || (gameObject2.getPosX() + gameObject2.getWidth() <= gameObject1.getPosX())
                || (gameObject2.getPosY()+gameObject2.getHeight() >= gameObject1.getPosY() + gameObject1.getHeight())
                || (gameObject2.getPosY()+gameObject2.getHeight()+9999999 <= gameObject1.getPosY()));
    }

    public static boolean gameObjectDessusX(GameObject gameObject1, GameObject gameObject2)
    {
        return !((gameObject2.getPosY() >= gameObject1.getPosY() + gameObject1.getHeight())
                || (gameObject2.getPosY() + gameObject2.getHeight() <= gameObject1.getPosY())
                || (0 >= gameObject1.getPosX() + gameObject1.getWidth())
                || (gameObject2.getPosX() <= gameObject1.getPosX()));
    }

    public static boolean gameObjectDessousX(GameObject gameObject1, GameObject gameObject2)
    {
        return !((gameObject2.getPosY() >= gameObject1.getPosY() + gameObject1.getHeight())
                || (gameObject2.getPosY() + gameObject2.getHeight() <= gameObject1.getPosY())
                || (gameObject2.getPosX()+gameObject2.getWidth() >= gameObject1.getPosX() + gameObject1.getWidth())
                || (gameObject2.getPosX()+gameObject2.getWidth()+9999999 <= gameObject1.getPosX()));
    }
}
