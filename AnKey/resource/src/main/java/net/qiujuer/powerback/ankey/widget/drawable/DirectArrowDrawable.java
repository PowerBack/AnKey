package net.qiujuer.powerback.ankey.widget.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

/**
 * Created by qiujuer
 * on 15/12/13.
 */
public class DirectArrowDrawable extends Drawable {
    public static final int DIRECT_LEFT = 1;
    public static final int DIRECT_TOP = 2;
    public static final int DIRECT_RIGHT = 3;
    public static final int DIRECT_BOTTOM = 4;

    private double mAngle;
    private int mDirect;
    private Path mPath;
    private Paint mPaint;
    private Rect mPadding;

    public DirectArrowDrawable(int angle, int direct, float lineSize, int color) {
        mAngle = Math.toRadians(angle / 2);
        mDirect = direct;
        mPath = new Path();

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(color);
        paint.setStrokeWidth(lineSize);
        paint.setStrokeCap(Paint.Cap.ROUND);
        mPaint = paint;

        mSPoint = new PointF();
        mCPoint = new PointF();
        mEPoint = new PointF();
    }

    public void setPadding(Rect padding) {
        if (padding == null) {
            mPadding = null;
        } else {
            if (mPadding == null) {
                mPadding = new Rect();
            }
            mPadding.set(padding);
        }
        invalidateSelf();
    }

    @Override
    public boolean getPadding(@NonNull Rect padding) {
        if (mPadding != null) {
            padding.set(mPadding);
            return true;
        } else {
            return super.getPadding(padding);
        }
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);

        float i = mPaint.getStrokeWidth() * 2f;
        float w = i;
        float h = i;
        if (mPadding != null) {
            w += mPadding.left + mPadding.right;
            h += mPadding.top + mPadding.bottom;
        }

        onResize(bounds.width() - w, bounds.height() - h);

    }

    private PointF mSPoint, mCPoint, mEPoint;

    protected void onResize(float width, float height) {
        mPath.reset();

        if (mDirect == DIRECT_RIGHT) {
            initRightArrowPoint(width, height);
        }

        mPath.moveTo(mSPoint.x, mSPoint.y);
        mPath.lineTo(mCPoint.x, mCPoint.y);
        mPath.lineTo(mEPoint.x, mEPoint.y);
    }


    private void initRightArrowPoint(float width, float height) {
        float sx, sy, cx, cy, ex, ey;

        cx = width;
        cy = height / 2;

        double tan = Math.tan(mAngle);
        float x = (float) (cy / tan);
        if (x > width) {
            sx = 0;
            sy = (float) (width * tan);
            sy = cy - sy;
            ey = height - sy;
        } else {
            x = width - x;
            sx = x;
            sy = 0;
            ey = height;
        }

        // Move to center
        float cex1 = width / 2;
        float cex2 = sx + (cx - sx) / 2;
        if (cex1 != cex2) {
            float dx = cex1 - cex2;
            sx += dx;
            cx += dx;
        }

        // the end x is start x
        ex = sx;

        mSPoint.set(sx, sy);
        mCPoint.set(cx, cy);
        mEPoint.set(ex, ey);
    }

    private void onDraw(Canvas canvas, Paint paint) {
        canvas.drawPath(mPath, paint);
    }

    @Override
    public void draw(Canvas canvas) {
        final Rect r = getBounds();
        float i = mPaint.getStrokeWidth();
        float w = i;
        float h = i;
        if (mPadding != null) {
            w += mPadding.left;
            h += mPadding.top;
        }

        final int count = canvas.save();
        canvas.translate(r.left + w, r.top + h);
        onDraw(canvas, mPaint);
        canvas.restoreToCount(count);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
