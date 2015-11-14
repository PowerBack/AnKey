package net.qiujuer.powerback.ankey.widget.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/**
 * Created by qiujuer
 * on 15/11/13.
 */
public class ShadowDrawable extends Drawable {
    private Paint mPaint;

    public ShadowDrawable() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(0xff345678);
        mPaint.setShadowLayer(5, 3, 6, 0xff000000);
        mPaint.setAntiAlias(true);//去除锯齿。
        mPaint.setShadowLayer(5f, 5.0f, 5.0f, Color.BLACK); //设置阴影层，这是关键。
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    }

    @Override
    public void draw(Canvas canvas) {
        Rect rect = getBounds();
        canvas.drawColor(0xffffffff);


        Shader mShader = new LinearGradient(0, 0, 0, 10, 0xffbbbbbb, 0xfffefefe, Shader.TileMode.REPEAT);
        // 新建一个线性渐变，前两个参数是渐变开始的点坐标，第三四个参数是渐变结束的点的坐标。
        // 连接这2个点就拉出一条渐变线了，玩过PS的都懂。然后那个数组是渐变的颜色。
        // 下一个参数是渐变颜色的分布，如果为空，每个颜色就是均匀分布的。最后是模式，这里设置的是循环渐变

        mPaint.setShader(mShader);
        mPaint.setColor(Color.WHITE);
        canvas.drawRect(rect.left, rect.bottom - 11, rect.right, rect.bottom - 1, mPaint);
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
