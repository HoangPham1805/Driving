// Road.java
package com.example.cyphercrash;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.cyphercrash.R;

public class Road {
    private Bitmap bitmap;
    private int screenWidth, screenHeight;
    private int y1, y2; // Two copies for scrolling

    public Road(Context context, int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        // Load road bitmap
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.road_background);
        bitmap = Bitmap.createScaledBitmap(bitmap, screenWidth, screenHeight, false);

        // Initial positions for seamless scrolling
        y1 = 0;
        y2 = -screenHeight;
    }

    public void update(int gameSpeed) {
        // Scroll the road
        y1 += gameSpeed;
        y2 += gameSpeed;

        // Reset positions for seamless scrolling
        if (y1 >= screenHeight) {
            y1 = -screenHeight + (y1 - screenHeight);
        }

        if (y2 >= screenHeight) {
            y2 = -screenHeight + (y2 - screenHeight);
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        // Draw both copies of the road
        canvas.drawBitmap(bitmap, 0, y1, paint);
        canvas.drawBitmap(bitmap, 0, y2, paint);
    }
}