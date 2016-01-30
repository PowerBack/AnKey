package net.qiujuer.powerback.ankey.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import net.qiujuer.genius.res.Resource;
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
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

        // Load attributes
        if (attrs != null) {
            final TypedArray a = context.obtainStyledAttributes(
                    attrs, R.styleable.ShadowLine, defStyleAttr, defStyleRes);
            mShadowColor = a.getColor(R.styleable.ShadowLine_shadowColor, mShadowColor);
            a.recycle();
        }

        setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint);
        mPaint.setColor(Resource.Color.BLACK);
        mPaint.setShadowLayer(20, 0, 0, mShadowColor);


        maskPaint.setAntiAlias(true);
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //
        zonePaint.setAntiAlias(true);
        zonePaint.setColor(Color.WHITE);
        //
        float density = getResources().getDisplayMetrics().density;
        rect_adius = rect_adius * density;
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
        //mPaint.setShadowLayer(10, 0, 0, mShadowColor);

        roundRect.set(0, 25, w, h);
    }

    private final RectF roundRect = new RectF();
    private float rect_adius = 0;
    private final Paint maskPaint = new Paint();
    private final Paint zonePaint = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.saveLayer(roundRect, zonePaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawRoundRect(roundRect, rect_adius, rect_adius, zonePaint);
        canvas.saveLayer(roundRect, maskPaint, Canvas.ALL_SAVE_FLAG);

        canvas.drawRect(0, 0, getWidth(), 1, mPaint);
        canvas.drawCircle(200, 50, 25, mPaint);

        canvas.restore();
    }
}
