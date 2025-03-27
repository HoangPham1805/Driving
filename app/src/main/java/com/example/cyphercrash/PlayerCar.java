// PlayerCar.java
package com.example.cyphercrash;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.cyphercrash.R;

public class PlayerCar {
    private Bitmap bitmap;
    private int x, y;
    private int width, height;
    private int screenWidth, screenHeight;
    private int laneWidth;
    private int currentLane;
    private final int TOTAL_LANES = 4;

    public PlayerCar(Context context, int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        // Load the car bitmap
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player_car);

        // Scale the bitmap
        int scaledWidth = screenWidth / 8;
        float aspectRatio = (float) bitmap.getHeight() / bitmap.getWidth();
        int scaledHeight = (int) (scaledWidth * aspectRatio);

        bitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, false);

        width = bitmap.getWidth();
        height = bitmap.getHeight();

        // Calculate lane width
        laneWidth = screenWidth / TOTAL_LANES;

        // Start in the middle lane (index 1 or 2 in a 0-3 system)
        currentLane = TOTAL_LANES / 2;

        // Position the car
        reset();
    }

    public void reset() {
        // Reset to middle lane
        currentLane = TOTAL_LANES / 2;

        // Position the car at the bottom of the screen
        x = (currentLane * laneWidth) + (laneWidth / 2) - (width / 2);
        y = screenHeight - height - 100; // 100 pixels from bottom
    }

    public void update() {
        // Update position if needed (for smooth lane transition animations)
        int targetX = (currentLane * laneWidth) + (laneWidth / 2) - (width / 2);

        // Simple animation for lane change
        if (x < targetX) {
            x += 20; // Adjust speed as needed
            if (x > targetX) x = targetX;
        } else if (x > targetX) {
            x -= 20; // Adjust speed as needed
            if (x < targetX) x = targetX;
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(bitmap, x, y, paint);
    }

    public void moveLeft() {
        if (currentLane > 0) {
            currentLane--;
        }
    }

    public void moveRight() {
        if (currentLane < TOTAL_LANES - 1) {
            currentLane++;
        }
    }

    // For collision detection
    public Rect getCollisionRect() {
        return new Rect(x, y, x + width, y + height);
    }

    // Getters for position
    public int getPosX() {
        return x;
    }

    public int getPosY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}