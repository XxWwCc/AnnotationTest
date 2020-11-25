package com.xwc.annotationtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Random;

public class ColoredTextView extends androidx.appcompat.widget.AppCompatTextView {

    private static final int[] COLORS = new int[] {Color.parseColor("#FF4500"),
            Color.parseColor("#3CB371"),
            Color.parseColor("#0000FF"),
            Color.parseColor("#1E90FF"),
            Color.parseColor("#FF8C00"),
            Color.parseColor("#FFBB86FC")};
    private static final int[] TEXT_SIZES = new int[] {15, 20, 10};
    private static final Random random = new Random();
    private static final int CORNER_RADIUS = (int) Utils.dp2px(4);
    private static final int X_PADDING = (int) Utils.dp2px(16);
    private static final int Y_PADDING = (int) Utils.dp2px(8);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public ColoredTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        setTextColor(Color.WHITE);
        setTextSize(TEXT_SIZES[random.nextInt(TEXT_SIZES.length)]);
        paint.setColor(COLORS[random.nextInt(COLORS.length)]);
        setPadding(X_PADDING, Y_PADDING, X_PADDING, Y_PADDING);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(0, 0, getWidth(), getHeight(), CORNER_RADIUS, CORNER_RADIUS, paint);
        super.onDraw(canvas);
    }
}
