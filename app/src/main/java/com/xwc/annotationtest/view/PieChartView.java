package com.xwc.annotationtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * describe: 饼图
 * author: xuweichao
 * date: 2020/9/16 16:18
 */
public class PieChartView extends View {
    private int curIndex = 5;
    private static final float LENGTH = Utils.dp2px(10);
    private static final float RADIUS = Utils.dp2px(75);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    RectF rectF = new RectF();
    int[] angles= {30,50,60,100,40,80};
    int[] colors = {Color.parseColor("#DC143C"),
            Color.parseColor("#1E90FF"),
            Color.parseColor("#3CB371"),
            Color.parseColor("#FF4500"),
            Color.parseColor("#FF8C00"),
            Color.parseColor("#0000FF"), };

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF.set(getWidth()/2 - RADIUS, getHeight()/2 - RADIUS, getWidth()/2 + RADIUS, getHeight()/2 + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int currAngle = 0;
        for (int i = 0; i < angles.length; i++) {
            paint.setColor(colors[i]);
            canvas.save();
            if (curIndex == i) {
                canvas.translate((float) Math.cos(Math.toRadians(currAngle + angles[i]/2)) * LENGTH,
                        (float) Math.sin(Math.toRadians(currAngle + angles[i]/2)) * LENGTH);
            }
            canvas.drawArc(rectF, currAngle, angles[i], true, paint);
            canvas.restore();
            currAngle += angles[i];
        }
    }
}
