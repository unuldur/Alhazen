package com.alhazen.defiolles.alhazen.Game;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.alhazen.defiolles.alhazen.FinishActivity;
import com.alhazen.defiolles.alhazen.Game.GameObject.Button;
import com.alhazen.defiolles.alhazen.Game.GameObject.MoveObject;
import com.alhazen.defiolles.alhazen.Game.GameObject.Player;
import com.alhazen.defiolles.alhazen.Game.Level.Level;
import com.alhazen.defiolles.alhazen.PauseActivity;
import com.alhazen.defiolles.alhazen.R;

public class GameView extends SurfaceView implements Runnable,SensorEventListener {

    Thread gameThread = null;
    SurfaceHolder ourHolder;

    volatile boolean playing;

    Canvas canvas;
    Paint paint;

    long fps;

    private long timeThisFrame;

    float walkSpeedPerSecond = 250;

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private Level level;

    private boolean peutCeLancer= false;


    private SpriteSheet buttonPause;
    private int numeroLevel;

    Player p;
    public GameView(Context context,Bundle savedInstanceState,int numeroLevel) {
        super(context);

        ourHolder = getHolder();
        paint = new Paint();
        this.numeroLevel = numeroLevel;
        senSensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        if(savedInstanceState == null) {
            //level = new Level(13, 18, R.drawable.map,getContext());
            level = new Level(numeroLevel,getContext());
            level.initializeTexture(getResources(), getResources().getDisplayMetrics().widthPixels
                    , getResources().getDisplayMetrics().heightPixels, getOrientation());
        }
        else{
            loadInstanceState(savedInstanceState);
            level.initializeTexture(getResources());
        }

        level.initializeOrientation(getOrientation());

        fps = 60;
        buttonPause = new SpriteSheet(getResources(),R.drawable.pause,1);
    }

    public void showPopup()
    {
        Intent intent = new Intent(getContext(), PauseActivity.class);
        intent.putExtra("LevelCours",numeroLevel);
        getContext().startActivity(intent);
    }

    private void levelFinish(){
        Intent intent = new Intent(getContext(), FinishActivity.class);
        intent.putExtra("LevelCour", numeroLevel);
        int levelfini = getContext().getSharedPreferences("LevelFini", Context.MODE_PRIVATE).getInt("LevelFini",1);
        if(levelfini +1 > numeroLevel)
            getContext().getSharedPreferences("LevelFini", Context.MODE_PRIVATE).edit().putInt("LevelFini",levelfini+1).apply();
        getContext().startActivity(intent);
        Activity activity = (Activity)getContext();
        activity.finish();
    }

    @Override
    public void run() {
        while (playing) {

            if(peutCeLancer)
                update();

            draw();

            if(level.isLevelFinish()) levelFinish();
        }

    }

    public void update() {

    level.move((int) (walkSpeedPerSecond / fps));
    level.updateFrameLevel();

    }

    public void draw() {
        try {
            if (ourHolder.getSurface().isValid()) {

                canvas = ourHolder.lockCanvas();

                Matrix m = new Matrix();
                int posXPause = 0;
                int posYPause = 0;
                buttonPause.setRotation(0);
                switch (getOrientation()) {
                    case Surface.ROTATION_90:
                        m.postRotate(270);
                        m.postTranslate(0, getHeight());
                        posXPause = getHeight() - buttonPause.getHeight();
                        posYPause = 0;
                        buttonPause.setRotation(90);
                        break;
                    case Surface.ROTATION_180:
                        m.postRotate(180);
                        m.postTranslate(getWidth(), getHeight());
                        posXPause = getWidth() ;
                        posYPause = getHeight()-buttonPause.getHeight();
                        buttonPause.setRotation(0);
                        break;
                    case Surface.ROTATION_270:
                        m.postRotate(90);
                        m.postTranslate(getWidth(), 0);
                        posXPause = 0;
                        posYPause = getWidth() - buttonPause.getWidth();
                        buttonPause.setRotation(90);
                        break;
                }
                buttonPause.setFrame(0);
                canvas.setMatrix(m);
                canvas.drawColor(Color.argb(255, 100, 100, 100));
                level.drawLevel(canvas, paint);
                buttonPause.drawCurrentFrame(canvas, posXPause, posYPause, paint);
                ourHolder.unlockCanvasAndPost(canvas);
                peutCeLancer = true;
            }
        }catch(IllegalArgumentException ignored)
        {

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void pause() {
        playing = false;

        try {
            gameThread.join();
            senSensorManager.unregisterListener(this);
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }

    }

    public void saveInstanceState(Bundle bundle)
    {
        bundle.putSerializable("Level", level);
    }

    public void loadInstanceState(Bundle load)
    {
        level = (Level)load.getSerializable("Level");

    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }






    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_MOVE:
                if(motionEvent.getX()>getWidth()/2)
                    level.movePlayer(Direction.DirectionEnum.RIGHT);
                else
                    level.movePlayer(Direction.DirectionEnum.LEFT);

                break;
            case MotionEvent.ACTION_DOWN:
                    if(motionEvent.getX()< 60 && motionEvent.getY()<60)
                        showPopup();

                break;

            case MotionEvent.ACTION_UP:

                level.movePlayer(Direction.DirectionEnum.CENTER);

                break;
        }
        return true;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            if(getOrientation() == Surface.ROTATION_0 || getOrientation() == Surface.ROTATION_180) {
                level.changeDirectectionY(y);
            }
            else
            {
                level.changeDirectectionY(x);
            }

        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private int getOrientation()
    {
        return ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
    }


}
