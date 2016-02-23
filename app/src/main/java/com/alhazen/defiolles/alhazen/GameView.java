package com.alhazen.defiolles.alhazen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
public class GameView extends SurfaceView implements Runnable,SensorEventListener {

    Thread gameThread = null;
    SurfaceHolder ourHolder;

    volatile boolean playing;

    Canvas canvas;
    Paint paint;

    long fps;

    private long timeThisFrame;

    // Bob starts off not moving
    boolean isMoving = false;

    // He can walk at 150 pixels per second
    float walkSpeedPerSecond = 250;

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private Level level;


    Player p;
    public GameView(Context context) {
        super(context);

        ourHolder = getHolder();
        paint = new Paint();

        senSensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);

        p =new Player(getResources(),R.drawable.perso,5,2,100,100,100);
        level = new Level(7,9,getResources(),R.drawable.map);
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

        if (ourHolder.getSurface().isValid()) {
            canvas = ourHolder.lockCanvas();
            canvas.drawColor(Color.argb(255,  26, 128, 182));
            paint.setColor(Color.argb(255, 249, 129, 0));
            p.draw(canvas,paint);
            level.drawLevel(canvas,paint);
            ourHolder.unlockCanvasAndPost(canvas);
        }

    }

    // If SimpleGameEngine Activity is paused/stopped
    // shutdown our thread.
    public void pause() {
        playing = false;
        senSensorManager.unregisterListener(this);
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }

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

            case MotionEvent.ACTION_DOWN:
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
        Sensor mySensor = sensorEvent.sensor;
        System.out.println(p.getDirectionY());
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            if(y< 0 )
                p.setDirectionY(Direction.DirectionEnum.TOP);
            else  p.setDirectionY(Direction.DirectionEnum.BOTTOM);

        }
        System.out.println(p.getDirectionY());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
