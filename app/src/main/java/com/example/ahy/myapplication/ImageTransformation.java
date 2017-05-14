package com.example.ahy.myapplication;

import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

/**
 * Created by ahy on 5/2/17.
 */

public class ImageTransformation implements Transformation {
    @Override public Bitmap transform(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 400;
        int y = (source.getHeight() - size) / 400;
        Bitmap result = Bitmap.createBitmap(source, x, y, size, size);
        if (result != source) {
            source.recycle();
        }
        return result;
    }

    @Override public String key() { return "square()"; }
}
