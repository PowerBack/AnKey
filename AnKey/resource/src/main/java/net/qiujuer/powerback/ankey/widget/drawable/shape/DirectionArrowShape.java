package net.qiujuer.powerback.ankey.widget.drawable.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.shapes.Shape;

/**
 * Created by qiujuer
 * on 15/12/12.
 */
public class DirectionArrowShape extends Shape{
    public static final int DIRECT_LEFT = 1;
    public static final int DIRECT_TOP = 2;
    public static final int DIRECT_RIGHT = 3;
    public static final int DIRECT_BOTTOM = 4;
    private double mAngle;
    private int mDirect;

    public DirectionArrowShape(int angle,int direct){
        mAngle = Math.toRadians(angle);
        mDirect = direct;
    }


    @Override
    public void draw(Canvas canvas, Paint paint) {

    }

    @Override
    protected void onResize(float width, float height) {
        if(mDirect==DIRECT_RIGHT){

        }

    }
}
