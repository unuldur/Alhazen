package com.alhazen.defiolles.alhazen.Game.Level;

import android.content.Context;

import com.alhazen.defiolles.alhazen.Game.Direction;
import com.alhazen.defiolles.alhazen.Game.GameObject.Arriver;
import com.alhazen.defiolles.alhazen.Game.GameObject.BaseLaser;
import com.alhazen.defiolles.alhazen.Game.GameObject.BlocMouvant;
import com.alhazen.defiolles.alhazen.Game.GameObject.Button;
import com.alhazen.defiolles.alhazen.Game.GameObject.InanimateObject;
import com.alhazen.defiolles.alhazen.Game.GameObject.MoveObject;
import com.alhazen.defiolles.alhazen.Game.GameObject.Mur;
import com.alhazen.defiolles.alhazen.Game.GameObject.Pic;
import com.alhazen.defiolles.alhazen.Game.GameObject.Player;
import com.alhazen.defiolles.alhazen.R;
import com.alhazen.defiolles.alhazen.Save.Save;
import com.alhazen.defiolles.alhazen.Save.SerializationSave;

import java.util.ArrayList;

/**
 * Created by PAYS on 08/03/2016.
 */
class SaveLevel {
    public static void saveLevel(int i,Context context)
    {
        switch (i)
        {
            case 999:
                test(context);
                break;
            case 1:
                level1(context);
                break;
            case 2:
                level2(context);
                break;
            case 10:
                level10(context);
                break;
            default:
                test(context);
        }
    }

    private static void test(Context context)
    {
        int id = R.drawable.map;
        int width = 13;
        int height = 18;
        InanimateObject[][] level = new InanimateObject[width][height];
        ArrayList<MoveObject> moveObjects= new ArrayList<>();
        Save save = new SerializationSave();

        for (int i =0 ;i< width ; i++)
        {
            level[i][0] = new Mur(id,3,5, Mur.TypeDeMur.BOTTOM);
            level[i][height-1] = new Mur(id,3,5, Mur.TypeDeMur.TOP);
        }

        for (int i =1 ;i< height-1 ; i++)
        {
            level[0][i] = new Mur(id,3,5, Mur.TypeDeMur.RIGHT);
            level[width-1][i] = new Mur(id,3,5, Mur.TypeDeMur.LEFT);
        }
        level[0][0] = new Mur(id,3,5, Mur.TypeDeMur.BOTTOM_RIGHT_IN);
        level[0][height-1] = new Mur(id,3,5, Mur.TypeDeMur.TOP_RIGHT_IN);
        level[width-1][0] = new Mur(id,3,5, Mur.TypeDeMur.BOTTOM_LEFT_IN);
        level[width-1][height-1] = new Mur(id,3,5, Mur.TypeDeMur.TOP_LEFT_IN);
        level[width-2][1] = new Arriver(R.drawable.arrive,1);
        level[1][1] = new BaseLaser(R.drawable.laser,8,2, Direction.DirectionEnum.RIGHT);

        level[4][16] = new Button(R.drawable.button,2,1,1,false,level[1][1]);




        int posXJoueurDepart = 3;
        int posYJoueurDepart = 4;

        moveObjects.add(new Player(R.drawable.perso, 5, 2, 100, 6,16));
        moveObjects.add(new BlocMouvant(R.drawable.cube,1,5,16));


        save.saveInanimateObjects(level, "level_999", context);
        save.saveObject("level_999_Move",context,moveObjects);
        save.savePosJoueur("level_999_Pos", context, posXJoueurDepart, posYJoueurDepart);
    }

    private static void level2(Context context)
    {
        int id = R.drawable.map;
        int width = 13;
        int height = 18;
        InanimateObject[][] level = new InanimateObject[width][height];
        ArrayList<MoveObject> moveObjects= new ArrayList<>();
        Save save = new SerializationSave();

        for (int i =0 ;i< width ; i++)
        {
            level[i][0] = new Mur(id,3,5, Mur.TypeDeMur.BOTTOM);
            level[i][4] = new Mur(id,3,5, Mur.TypeDeMur.TOP);
        }

        for (int i =1 ;i< 4 ; i++)
        {
            level[0][i] = new Mur(id,3,5, Mur.TypeDeMur.RIGHT);
            level[width-1][i] = new Mur(id,3,5, Mur.TypeDeMur.LEFT);
        }
        level[0][0] = new Mur(id,3,5, Mur.TypeDeMur.BOTTOM_RIGHT_IN);
        level[0][4] = new Mur(id,3,5, Mur.TypeDeMur.TOP_RIGHT_IN);
        level[width-1][0] = new Mur(id,3,5, Mur.TypeDeMur.BOTTOM_LEFT_IN);
        level[width-1][4] = new Mur(id,3,5, Mur.TypeDeMur.TOP_LEFT_IN);
        level[width-2][1] = new Arriver(R.drawable.arrive,1);




        int posXJoueurDepart = 3;
        int posYJoueurDepart = 4;
        moveObjects.add(new Player(R.drawable.perso, 5, 2, 100, 3, 3));

        save.saveInanimateObjects(level, "level_2", context);
        save.saveObject("level_2_Move",context,moveObjects);
        save.savePosJoueur("level_2_Pos", context, posXJoueurDepart, posYJoueurDepart);
    }

    private static void level1(Context context)
    {
        int id = R.drawable.map;
        int width = 13;
        int height = 18;
        InanimateObject[][] level = new InanimateObject[width][height];
        ArrayList<MoveObject> moveObjects= new ArrayList<>();
        Save save = new SerializationSave();

        for (int i =0 ;i< width ; i++)
        {
            level[i][0] = new Mur(id,3,5, Mur.TypeDeMur.BOTTOM);
            level[i][4] = new Mur(id,3,5, Mur.TypeDeMur.TOP);
        }

        for (int i =1 ;i< 4 ; i++)
        {
            level[0][i] = new Mur(id,3,5, Mur.TypeDeMur.RIGHT);
            level[width-1][i] = new Mur(id,3,5, Mur.TypeDeMur.LEFT);
        }
        level[0][0] = new Mur(id,3,5, Mur.TypeDeMur.BOTTOM_RIGHT_IN);
        level[0][4] = new Mur(id,3,5, Mur.TypeDeMur.TOP_RIGHT_IN);
        level[width-1][0] = new Mur(id,3,5, Mur.TypeDeMur.BOTTOM_LEFT_IN);
        level[width-1][4] = new Mur(id,3,5, Mur.TypeDeMur.TOP_LEFT_IN);
        level[width-2][1] = new Arriver(R.drawable.arrive,1);
        level[1][1] = new BaseLaser(R.drawable.laser,8,2, Direction.DirectionEnum.RIGHT);




         int posXJoueurDepart = 3;
         int posYJoueurDepart = 4;
        moveObjects.add(new Player(R.drawable.perso, 5, 2, 100, 3, 3));
        moveObjects.add(new BlocMouvant(R.drawable.cube,1,4,3));

        save.saveInanimateObjects(level, "level_1", context);
        save.saveObject("level_1_Move",context,moveObjects);
        save.savePosJoueur("level_1_Pos", context, posXJoueurDepart, posYJoueurDepart);
    }

    private static void level10(Context context)
    {
        int id = R.drawable.map;
        int width = 13;
        int height = 18;
        InanimateObject[][] level = new InanimateObject[width][height];
        ArrayList<MoveObject> moveObjects= new ArrayList<>();
        Save save = new SerializationSave();

        for (int i =0 ;i< width ; i++)
        {
            level[i][0] = new Mur(id,3,5, Mur.TypeDeMur.CENTER);
            level[i][height-1] = new Mur(id,3,5, Mur.TypeDeMur.TOP);
            level[i][1] = new Mur(id,3,5, Mur.TypeDeMur.CENTER);
            level[i][2] = new Mur(id,3,5, Mur.TypeDeMur.CENTER);
            level[i][3] = new Mur(id,3,5, Mur.TypeDeMur.BOTTOM);
        }

        for (int i =1 ;i< height-1 ; i++)
        {
            level[0][i] = new Mur(id,3,5, Mur.TypeDeMur.RIGHT);
            level[width-1][i] = new Mur(id,3,5, Mur.TypeDeMur.LEFT);
        }
        level[0][3] = new Mur(id,3,5, Mur.TypeDeMur.BOTTOM_RIGHT_IN);
        level[0][height-1] = new Mur(id,3,5, Mur.TypeDeMur.TOP_RIGHT_IN);
        level[width-1][3] = new Mur(id,3,5, Mur.TypeDeMur.BOTTOM_LEFT_IN);
        level[width-1][height-1] = new Mur(id,3,5, Mur.TypeDeMur.TOP_LEFT_IN);
        level[0][2] = new Mur(id,3,5, Mur.TypeDeMur.CENTER);
        level[0][1] = new Mur(id,3,5, Mur.TypeDeMur.CENTER);
        level[width-1][2] = new Mur(id,3,5, Mur.TypeDeMur.CENTER);
        level[width-1][1] = new Mur(id,3,5, Mur.TypeDeMur.CENTER);

        level[6][1] = new Arriver(R.drawable.arrive,1);
        level[1][16] = new Button(R.drawable.button,2,6,2,false,level[6][2]);
        level[width-2][16] = new Button(R.drawable.button,2,6,3,false,level[6][3]);

        level[2][16] = new Pic(R.drawable.pic,4, Direction.DirectionEnum.TOP);
        level[3][16] = new Pic(R.drawable.pic,4, Direction.DirectionEnum.TOP);
        level[4][16] = new Pic(R.drawable.pic,4, Direction.DirectionEnum.TOP);
        level[6][16] = new Pic(R.drawable.pic,4, Direction.DirectionEnum.TOP);
        level[7][16] = new Pic(R.drawable.pic,4, Direction.DirectionEnum.TOP);
        level[8][16] = new Pic(R.drawable.pic,4, Direction.DirectionEnum.TOP);

        moveObjects.add(new Player(R.drawable.perso, 5, 2, 100, 6, 16));



        save.saveInanimateObjects(level, "level_10", context);
        save.saveObject("level_10_Move", context, moveObjects);
    }
}
