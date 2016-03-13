package com.alhazen.defiolles.alhazen.Save;

import android.content.Context;

import com.alhazen.defiolles.alhazen.Game.GameObject.InanimateObject;
import com.alhazen.defiolles.alhazen.Game.GameObject.MoveObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by PAYS on 06/03/2016.
 */
public class SerializationSave extends Save {

    @Override
    public void saveInanimateObjects(InanimateObject[][] inanimateObjects,String name,Context context) {
        ObjectOutputStream oos = null;

        try {
            final FileOutputStream fichier = context.openFileOutput(name,Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(inanimateObjects);
            oos.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void saveObject(String name, Context context, ArrayList<MoveObject> tab) {
        ObjectOutputStream oos = null;

        try {
            final FileOutputStream fichier = context.openFileOutput(name,Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(tab);
            oos.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void savePosJoueur(String name, Context context, int posX, int posY) {
        ObjectOutputStream oos = null;

        try {
            final FileOutputStream fichier = context.openFileOutput(name,Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(new int[]{posX,posY});
            oos.flush();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
