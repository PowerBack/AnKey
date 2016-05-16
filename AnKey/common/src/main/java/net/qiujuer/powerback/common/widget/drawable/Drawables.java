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

import android.content.res.Resources;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;

import net.qiujuer.powerback.common.widget.drawable.shape.CrossLineShape;
import net.qiujuer.powerback.common.widget.drawable.shape.OkLineShape;
import net.qiujuer.powerback.common.widget.drawable.shape.ShadowShape;


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
