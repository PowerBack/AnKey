package net.qiujuer.powerback.ankey.widget.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.shapes.Shape;

/**
 * Created by qiujuer
 * on 15/11/25.
 */
public class ShadowShape extends Shape {
    private int mColor;
    private float mShadowLen;

    public ShadowShape(int color, float shadowLen) {
        mColor = color;
        mShadowLen = shadowLen;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float w = getWidth();
        float h = getHeight();

        float sh = h - mShadowLen;

        // draw the background
        int sc = canvas.save();
        canvas.clipRect(0, 0, w, sh);
        canvas.drawColor(mColor);
        canvas.restoreToCount(sc);

        canvas.drawRect(0, sh, w, h, paint);
    }
}
