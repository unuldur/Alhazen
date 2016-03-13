package com.alhazen.defiolles.alhazen.Load;

import android.content.Context;

import com.alhazen.defiolles.alhazen.Game.GameObject.InanimateObject;
import com.alhazen.defiolles.alhazen.Game.GameObject.MoveObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by PAYS on 06/03/2016.
 */
public class SerializationLoad extends Load{
    @Override
    public InanimateObject[][] loadInanimate(String name,Context context) {
        ObjectInputStream ois = null;

        try {
            final FileInputStream fichier = context.openFileInput(name);
            ois = new ObjectInputStream(fichier);
            final InanimateObject[][] tab = (InanimateObject[][]) ois.readObject();
            return tab;
        } catch (final java.io.IOException e) {
            e.printStackTrace();
            return null;
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<MoveObject> loadObject(String name, Context context) {
        ObjectInputStream ois = null;

        try {
            final FileInputStream fichier = context.openFileInput(name);
            ois = new ObjectInputStream(fichier);
            final ArrayList<MoveObject> tab = (ArrayList<MoveObject>) ois.readObject();
            return tab;
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int[] loadPosJoueur(String name, Context context) {
        ObjectInputStream ois = null;

        try {
            final FileInputStream fichier = context.openFileInput(name);
            ois = new ObjectInputStream(fichier);
            final int[] tab = (int[]) ois.readObject();
            return tab;
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
