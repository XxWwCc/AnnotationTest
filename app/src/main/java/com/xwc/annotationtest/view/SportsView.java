package com.xwc.annotationtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.StaticLayout;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.xwc.annotationtest.R;

public class SportsView extends View {

    private float RADIUS = Utils.dp2px(100);
    private float STROKE_WIDTH = Utils.dp2px(10);
    private Paint paint = new Paint(1);
    private Context mContext;
    private int RED;
    private int ORANGE;
    private String sportText = "abcd";
    private Rect rect = new Rect();
    private Paint.FontMetrics fontMetrics = new Paint.FontMetrics();

    public SportsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        RED = ContextCompat.getColor(mContext, R.color.color_FF4500);
        ORANGE = ContextCompat.getColor(mContext, R.color.color_FF8C00);
    }

    {
        paint.setTextSize(Utils.dp2px(50));
        paint.setTextAlign(Paint.Align.CENTER);
        // 获取文字的边界，适用于动态文字
        paint.getFontMetrics(fontMetrics);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(ORANGE);
        paint.setStrokeWidth(STROKE_WIDTH);
        canvas.drawCircle(getWidth()/2, getWidth()/2, RADIUS, paint);

        paint.setColor(RED);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(getWidth()/2-RADIUS, getHeight()/2-RADIUS, getWidth()/2+RADIUS, getHeight()/2+RADIUS, -90, 225, false, paint);

        paint.setStyle(Paint.Style.FILL);
        // 适用于静态文字
//        paint.getTextBounds(sportText, 0, sportText.length(), rect);
//        int offset = (rect.top + rect.bottom) /2;
//        canvas.drawText(sportText, getWidth()/2, getHeight()/2-offset, paint);
        float offset = (fontMetrics.ascent + fontMetrics.descent) / 2;
        canvas.drawText(sportText, getWidth()/2, getHeight()/2-offset, paint);

    }
}
