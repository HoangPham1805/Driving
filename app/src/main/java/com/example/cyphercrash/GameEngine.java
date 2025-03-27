package com.example.cyphercrash;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.os.Vibrator;
public class GameEngine extends SurfaceView implements Runnable{
    private boolean isPlaying;
    private boolean isGameOver;
    // Game Thread

    // Game object
    private PlayerCar playerCar;
    private Obstacle obstacle;
    private Road road;
    private Score score;

    // Drawing objects
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Paint paint;

    // Screen dimensions
    private int screenWidth;
    private int screenHeight;

    // Game timing
    private long fps;
    private long timeThisFrame;
    private long lastSpeedIncreaseTime;

    // Game speed
    private int gameSpeed;
    private final int SPEED_INCREASE_INTERVAL = 15000; // 15 seconds

    // Context for resources
    private Context context;

    // Vibrator for crash feedback
    private Vibrator vibrator;
    private ObstacleManager obstacleManager;

    public GameEngine(Context context, int screenWidth, int screenHeight) {
        super(context);

        this.context = context;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        // Initialize the drawing objects
        surfaceHolder = getHolder();
        paint = new Paint();

        // Initialize game objects
        playerCar = new PlayerCar(context, screenWidth, screenHeight);
        road = new Road(context, screenWidth, screenHeight);
        obstacleManager = new ObstacleManager(context, screenWidth, screenHeight);
        score = new Score();

        // Initialize game state
        isPlaying = false;
        isGameOver = false;
        gameSpeed = 5; // Initial game speed

        private void resetGame() {
            playerCar.reset();
            obstacleManager.reset();
            score.reset();

            isGameOver = false;
            gameSpeed = 5;
            lastSpeedIncreaseTime = System.currentTimeMillis();
        }
    }
    private void resetGame () {
        playerCar.reset();
        obstacleManager.reset();
        score.reset();

        isGameOver = false;
        gameSpeed = 5;
        lastSpeedIncreaseTime = System.currentTimeMillis();
    }
    @Override
    public void run() {
        while (isPlaying) {
            long startFrameTime = System.currentTimeMillis();

            if (!isGameOver) {
                update();
            }

            draw();

            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame > 0) {
                fps = 1000 / timeThisFrame;
            }
        }
    }

    public void pause(){

    }

    public void resume(){

    }

    public boolean onTouchEvent(){

        return false;
    }
}
