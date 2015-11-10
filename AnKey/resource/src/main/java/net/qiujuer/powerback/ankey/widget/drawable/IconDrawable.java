package net.qiujuer.powerback.ankey.widget.drawable;

import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;

/**
 * Created by qiujuer
 * on 15/11/9.
 */
public class IconDrawable {
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
}
