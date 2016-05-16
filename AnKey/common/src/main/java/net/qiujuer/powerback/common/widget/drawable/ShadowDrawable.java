/*
 * Copyright (C) 2015-2016 Qiujuer-PowerBack <qiujuer@live.cn>
 * WebSite https://github.com/PowerBack/AnKey
 * Author Qiujuer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.qiujuer.powerback.common.widget.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import net.qiujuer.genius.ui.Ui;

public class ShadowDrawable extends Drawable {
    private Paint mPaint;
    private Rect mPadding = new Rect(0, 25, 0, 0);
    private int mColor;
    private int mShadowLen = 10;

    public ShadowDrawable(int color, int shadowLen) {
        mColor = color;
        mShadowLen = shadowLen;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(1);

        //mPaint.setStrokeJoin(Paint.Join.ROUND);
        //mPaint.setStrokeCap(Paint.Cap.ROUND);

        initShader();
    }

    public void setColor(int color) {
        mColor = color;
        initShader();
    }

    public void setShadowLen(int len) {
        mShadowLen = len;
        initShader();
    }

    private void initShader() {
        Shader shader = new LinearGradient(0, 0, 0, mShadowLen, 0xffbbbbbb, 0xfefefefe, Shader.TileMode.REPEAT);
        mPaint.setColor(mColor);
        //mPaint.setShader(shader);
    }

    public Paint getPaint() {
        return mPaint;
    }

    @Override
    public void draw(Canvas canvas) {
        Rect rect = getBounds();

        // draw the src/dst example into our offscreen bitmap
        int sc = canvas.saveLayer(rect.left, rect.top, rect.right, rect.bottom, null,
                Canvas.MATRIX_SAVE_FLAG |
                        Canvas.CLIP_SAVE_FLAG |
                        Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                        Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                        Canvas.CLIP_TO_LAYER_SAVE_FLAG);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        mPaint.setColor(Color.TRANSPARENT);
        canvas.drawRect(rect.left, rect.top, rect.right, rect.bottom, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(sc);


        int bottom = rect.bottom - 1;

        mPaint.setColor(mColor);
        canvas.drawRect(rect.left, rect.top, rect.right, bottom - mShadowLen, mPaint);

        drawBottomShadow(canvas, rect.left, rect.right, bottom - mShadowLen, bottom - 1, 1);
    }

    private void drawBottomShadow(Canvas canvas, float startX, float endX, float startY, float endY, float precision) {
        final float h = endY - startY;
        for (float a = startY; a <= endY; a += precision) {
            float fraction = (a - startY) / h;
            int color = getColor(0x99000000, Color.TRANSPARENT, fraction);
            mPaint.setColor(color);
            canvas.drawRect(startX, a, endX, a + precision, mPaint);
        }
    }

    private int getColor(int startColor, int endColor, float fraction) {
        // Color
        int startA = (startColor >> 24) & 0xff;
        int startR = (startColor >> 16) & 0xff;
        int startG = (startColor >> 8) & 0xff;
        int startB = startColor & 0xff;

        int endA = (endColor >> 24) & 0xff;
        int endR = (endColor >> 16) & 0xff;
        int endG = (endColor >> 8) & 0xff;
        int endB = endColor & 0xff;

        return (startA + (int) (fraction * (endA - startA))) << 24 |
                (startR + (int) (fraction * (endR - startR))) << 16 |
                (startG + (int) (fraction * (endG - startG))) << 8 |
                (startB + (int) (fraction * (endB - startB)));
    }

    @Override
    public void setAlpha(int alpha) {
        Ui.changeColorAlpha(mColor, alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        final Paint paint = mPaint;
        if (paint != null && paint.getColorFilter() != colorFilter) {
            paint.setColorFilter(colorFilter);
            invalidateSelf();
        }
    }

    public void setPadding(Rect padding) {
        this.mPadding.set(padding);
    }

    @Override
    public boolean getPadding(Rect padding) {
        padding.set(mPadding);
        return true;
    }

    @Override
    public int getOpacity() {
        final Paint paint = mPaint;
        if (paint.getXfermode() == null) {
            final int alpha = Color.alpha(mColor);
            if (alpha == 0) {
                return PixelFormat.TRANSPARENT;
            }
            if (alpha == 255) {
                return PixelFormat.OPAQUE;
            }
        }
        // not sure, so be safe
        return PixelFormat.TRANSLUCENT;
    }
}
