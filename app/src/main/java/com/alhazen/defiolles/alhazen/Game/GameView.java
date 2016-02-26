package com.alhazen.defiolles.alhazen.Game;

import android.content.Context;
import android.content.res.Configuration;
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
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.alhazen.defiolles.alhazen.Game.GameObject.Player;
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


    Player p;
    public GameView(Context context,Bundle savedInstanceState) {
        super(context);

        ourHolder = getHolder();
        paint = new Paint();

        senSensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        if(savedInstanceState == null) {
            p = new Player(R.drawable.perso, 5, 2, 100, 200, 200);
            level = new Level(13, 18, R.drawable.map);
            level.initializeTextureMurs(getResources(), getResources().getDisplayMetrics().widthPixels
                    , getResources().getDisplayMetrics().heightPixels);
        }
        else{
            loadInstanceState(savedInstanceState);
            level.initializeTextureMurs(getResources());
        }
        p.initializeSprite(getResources());
        p.setOrientation(getOrientation());
        fps = 60;
    }

    @Override
    public void run() {
        while (playing) {

            long startFrameTime = System.currentTimeMillis();

            update();

            draw();

            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame;
            }

        }

    }

    public void update() {

       p.move((int) (walkSpeedPerSecond / fps), level);
       p.updateFrame();

    }

    public void draw() {
        try {
            if (ourHolder.getSurface().isValid()) {
                canvas = ourHolder.lockCanvas();
                Matrix m = new Matrix();
                switch (getOrientation()) {
                    case Surface.ROTATION_90:
                        m.postRotate(270);
                        m.postTranslate(0, getHeight());
                        break;
                    case Surface.ROTATION_180:
                        m.postRotate(180);
                        m.postTranslate(getWidth(), getHeight());
                        break;
                    case Surface.ROTATION_270:
                        m.postRotate(90);
                        m.postTranslate(getWidth(), 0);
                        break;
                }
                canvas.setMatrix(m);
                canvas.drawColor(Color.argb(255, 100, 100, 100));
                p.draw(canvas, paint);
                level.drawLevel(canvas, paint);
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }catch(IllegalArgumentException ignored)
        {

        }

    }

    public void pause() {
        playing = false;
        senSensorManager.unregisterListener(this);
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }

    }

    public void saveInstanceState(Bundle bundle)
    {
        bundle.putSerializable("Level",level);
        bundle.putSerializable("Player",p);
        bundle.putInt("Orientation", getContext().getResources().getConfiguration().orientation);
    }

    public void loadInstanceState(Bundle load)
    {
        p= (Player)load.getSerializable("Player");
        level = (Level)load.getSerializable("Level");
        p.setOrientation(getOrientation());
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
                    p.setDirectionX(Direction.DirectionEnum.RIGHT);
                else
                    p.setDirectionX(Direction.DirectionEnum.LEFT);

                break;

            case MotionEvent.ACTION_UP:

                p.setDirectionX(Direction.DirectionEnum.CENTER);

                break;
        }
        return true;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(!p.isAuSol()) return;
        Sensor mySensor = sensorEvent.sensor;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            if(getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                changeDirectectionY(y);
            }
            else
            {
                changeDirectectionY(x);
            }

        }
    }

    private void changeDirectectionY(float val)
    {
        if (val < 1) {
            if (p.getDirectionY() == Direction.DirectionEnum.BOTTOM) {
                p.setDirectionY(Direction.DirectionEnum.TOP);
                p.setAuSol(false);
                p.turnThis();
            }
        } else {
            if (p.getDirectionY() == Direction.DirectionEnum.TOP) {
                p.setDirectionY(Direction.DirectionEnum.BOTTOM);
                p.setAuSol(false);
                p.turnThis();
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
