package com.alhazen.defiolles.alhazen.Save;

import android.content.Context;

import com.alhazen.defiolles.alhazen.Game.GameObject.InanimateObject;
import com.alhazen.defiolles.alhazen.Game.GameObject.MoveObject;

import java.util.ArrayList;

/**
 * Created by PAYS on 06/03/2016.
 */
public abstract class Save {
    public abstract void saveInanimateObjects(InanimateObject[][] inanimateObjects,String name,Context context);
    public abstract void saveObject(String name, Context context,ArrayList<MoveObject> tab);
    public abstract void savePosJoueur(String name,Context context,int posX,int posY);
}
