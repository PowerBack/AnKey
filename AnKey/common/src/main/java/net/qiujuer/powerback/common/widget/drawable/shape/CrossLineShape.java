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
package net.qiujuer.powerback.common.widget.drawable.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.shapes.Shape;

public class CrossLineShape extends Shape {
    private float mCenterX, mCenterY;
    private float mHalfLong;

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float topY = mCenterY - mHalfLong;
        float bottomY = mCenterY + mHalfLong;

        canvas.drawLine(mCenterX, topY, mCenterX, bottomY, paint);


        float leftX = mCenterX - mHalfLong;
        float rightX = mCenterX + mHalfLong;

        canvas.drawLine(leftX, mCenterY, rightX, mCenterY, paint);
    }

    @Override
    protected void onResize(float width, float height) {
        mCenterX = width / 2;
        mCenterY = height / 2;

        float minLong = Math.min(width, height);
        mHalfLong = minLong * 0.2f;
    }
}
