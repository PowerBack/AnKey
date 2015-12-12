package net.qiujuer.powerback.ankey.widget.drawable.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.shapes.Shape;

/**
 * Created by qiujuer
 * on 15/10/25.
 */
public class OkLineShape extends Shape {
    private PointF mStart = new PointF();
    private PointF mCenter = new PointF();
    private PointF mStop = new PointF();

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawLine(mStart.x, mStart.y, mCenter.x, mCenter.y, paint);
        canvas.drawLine(mCenter.x, mCenter.y, mStop.x, mStop.y, paint);
    }

    @Override
    protected void onResize(float width, float height) {
        float tw = width * 0.3f;
        float th = height * 0.35f;

        mStart.set(tw, height * 0.5f);
        mCenter.set(width * 0.46f, height - th);
        mStop.set(width - tw, th * 0.9f);
    }
}
