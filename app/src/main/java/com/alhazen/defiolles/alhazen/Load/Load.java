package com.alhazen.defiolles.alhazen.Load;

import android.content.Context;

import com.alhazen.defiolles.alhazen.Game.GameObject.InanimateObject;
import com.alhazen.defiolles.alhazen.Game.GameObject.MoveObject;

import java.util.ArrayList;

/**
 * Created by PAYS on 06/03/2016.
 */
public abstract class Load {
    public abstract InanimateObject[][] loadInanimate(String name, Context context);
    public abstract ArrayList<MoveObject> loadObject(String name, Context context);
    public abstract int[] loadPosJoueur(String name,Context context);
}
