package net.qiujuer.powerback.ankey.widget.drawable;

import android.content.res.Resources;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;

import net.qiujuer.powerback.ankey.widget.drawable.shape.CrossLineShape;
import net.qiujuer.powerback.ankey.widget.drawable.shape.OkLineShape;
import net.qiujuer.powerback.ankey.widget.drawable.shape.ShadowShape;

/**
 * Created by qiujuer
 * on 15/11/9.
 */
public class Drawables {
    private static ShapeDrawable createShapeDrawable(Resources resources, Shape shape, int color, int size, int lineSize) {
        final float density = resources.getDisplayMetrics().density;
        ShapeDrawable drawable = new ShapeDrawable(shape);
        Paint paint = drawable.getPaint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(color);
        paint.setStrokeWidth(lineSize * density);
        paint.setStrokeCap(Paint.Cap.ROUND);
        drawable.setIntrinsicWidth((int) (size * density));
        drawable.setIntrinsicHeight((int) (size * density));
        return drawable;
    }

    private static Rect convertDPToPX(Rect rect, float density) {
        if (rect != null) {
            rect.left *= density;
            rect.top *= density;
            rect.right *= density;
            rect.bottom *= density;
        }
        return rect;
    }

    public static Drawable getCreateDrawable(Resources resources) {
        Shape shape = new CrossLineShape();
        return createShapeDrawable(resources, shape, 0xfffbfbfb, 36, 2);
    }

    public static Drawable getOkDrawable(Resources resources) {
        Shape shape = new OkLineShape();
        return createShapeDrawable(resources, shape, 0xfffbfbfb, 36, 2);
    }

    public static Drawable getDirectDrawable(Resources resources, int angle, int direct, Rect padding) {
        final float density = resources.getDisplayMetrics().density;
        DirectArrowDrawable directArrowDrawable = new DirectArrowDrawable(angle, direct, density * 2, 0xfffbfbfb);
        directArrowDrawable.setPadding(convertDPToPX(padding, density));
        return directArrowDrawable;
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
