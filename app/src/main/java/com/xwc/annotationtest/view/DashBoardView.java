package com.xwc.annotationtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * describe: 仪表盘
 * author: xuweichao
 * date: 2020/9/16 14:35
 */
public class DashBoardView extends View {
    private int ANGLE = 120;
    private float RADIUS = Utils.dp2px(75);
    private float LENGTH = Utils.dp2px(50);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path dash = new Path();
    PathDashPathEffect effect;

    public DashBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        RADIUS = Math.min(getWidth(), getHeight())/2;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dp2px(2));
        dash.addRect(0,0, Utils.dp2px(2), Utils.dp2px(5), Path.Direction.CW);
        Path arc = new Path();
        arc.addArc(getWidth()/2-RADIUS,
                getHeight()/2-RADIUS,
                getWidth()/2+RADIUS,
                getHeight()/2+RADIUS,
                90+ANGLE/2,
                360-ANGLE);
        PathMeasure pathMeasure = new PathMeasure(arc, false);
                                                                                // 减去最后一个刻度的宽度
        effect = new PathDashPathEffect(dash, (pathMeasure.getLength()-Utils.dp2px(2))/20, 0, PathDashPathEffect.Style.ROTATE);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(getWidth()/2-RADIUS,
                getHeight()/2-RADIUS,
                getWidth()/2+RADIUS,
                getHeight()/2+RADIUS,
                90+ANGLE/2,
                360-ANGLE,
                false, paint);

        paint.setPathEffect(effect);
        canvas.drawArc(getWidth()/2-RADIUS,
                getHeight()/2-RADIUS,
                getWidth()/2+RADIUS,
                getHeight()/2+RADIUS,
                90+ANGLE/2,
                360-ANGLE,
                false, paint);
        paint.setPathEffect(null);

        // x cos值 y sin值
        canvas.drawLine(getWidth()/2,
                getHeight()/2,
                (float) Math.cos(Math.toRadians(getAngleFromMark(1))) * LENGTH + getWidth()/2,
                (float) Math.sin(Math.toRadians(getAngleFromMark(1))) * LENGTH + getHeight()/2,
                paint);
    }

    int getAngleFromMark(int mark) {
        return (int) (90+(float)ANGLE/2 + (360-(float)ANGLE)/20*mark);
    }
}
