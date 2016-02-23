package com.alhazen.defiolles.alhazen;

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
}
