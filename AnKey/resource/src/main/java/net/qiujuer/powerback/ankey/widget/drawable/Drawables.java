package net.qiujuer.powerback.ankey.widget.drawable;

import android.content.res.Resources;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;

/**
 * Created by qiujuer
 * on 15/11/9.
 */
public class Drawables {
    public static Drawable getCreateDrawable(Resources resources) {
        final float density = resources.getDisplayMetrics().density;
        Shape shape = new CrossLineShape();
        ShapeDrawable drawable = new ShapeDrawable(shape);
        Paint paint = drawable.getPaint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(0xfffbfbfb);
        paint.setStrokeWidth(2 * density);
        paint.setStrokeCap(Paint.Cap.ROUND);
        drawable.setIntrinsicWidth((int) (36 * density));
        drawable.setIntrinsicHeight((int) (36 * density));
        return drawable;
    }

    public static Drawable getOkDrawable(Resources resources) {
        final float density = resources.getDisplayMetrics().density;
        Shape shape = new OkLineShape();
        ShapeDrawable drawable = new ShapeDrawable(shape);
        Paint paint = drawable.getPaint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(0xfffbfbfb);
        paint.setStrokeWidth(2 * density);
        paint.setStrokeCap(Paint.Cap.ROUND);
        drawable.setIntrinsicWidth((int) (36 * density));
        drawable.setIntrinsicHeight((int) (36 * density));
        return drawable;
    }

    public static Drawable getShadowDrawable(Resources resources, int color) {
        return getShadowDrawable(resources, color, 0x80000000, 4);
    }

    public static Drawable getShadowDrawable(Resources resources, int color, int sColor, int sLen) {
        final float density = resources.getDisplayMetrics().density;
        float sLenPx = sLen * density;

        ShadowShape shape = new ShadowShape(color, sLenPx);
        ShapeDrawable drawable = new ShapeDrawable(shape);

        Paint paint = drawable.getPaint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(sColor);

        Shader shader = new LinearGradient(0, 0, 0, sLenPx, sColor, 0x00000000, Shader.TileMode.REPEAT);
        paint.setShader(shader);

        return drawable;
    }
}
