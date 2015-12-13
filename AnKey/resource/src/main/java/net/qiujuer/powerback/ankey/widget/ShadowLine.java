package net.qiujuer.powerback.ankey.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import net.qiujuer.genius.ui.Ui;
import net.qiujuer.powerback.ankey.resource.R;

/**
 * Created by qiujuer
 * on 15/12/13.
 */
public class ShadowLine extends View {
    private Paint mPaint;
    private int mShadowColor = Ui.KEY_SHADOW_COLOR;

    public ShadowLine(Context context) {
        super(context);
        init(null, 0, 0);
    }

    public ShadowLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0, 0);
    }

    public ShadowLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ShadowLine(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr, defStyleRes);
    }

    private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        final Context context = getContext();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeWidth(0);

        // Load attributes
        if (attrs != null) {
            final TypedArray a = context.obtainStyledAttributes(
                    attrs, R.styleable.ShadowLine, defStyleAttr, defStyleRes);
            mShadowColor = a.getColor(R.styleable.ShadowLine_shadowColor, mShadowColor);
            a.recycle();
        }

        setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint);
        mPaint.setColor(Ui.FILL_SHADOW_COLOR);
    }

    public void setShadowColor(int shadowColor) {
        this.mShadowColor = shadowColor;
    }

    public int getShadowColor() {
        return mShadowColor;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPaint.setShadowLayer(h, 0, h, mShadowColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, getWidth(), 0, mPaint);
    }
}
