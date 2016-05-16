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
import android.graphics.PointF;
import android.graphics.drawable.shapes.Shape;


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
