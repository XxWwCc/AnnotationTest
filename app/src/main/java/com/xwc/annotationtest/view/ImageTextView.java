package com.xwc.annotationtest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.xwc.annotationtest.R;

public class ImageTextView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float TEXT_SIZE = Utils.dp2px(13);
    private Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
    private float IMAGE_Y_OFFSET = Utils.dp2px(50);
    private int IMAGE_WIDTH = (int)Utils.dp2px(100);
    private String text = "";
    private float[] measuredTextWidth = new float[1];

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        text = context.getResources().getString(R.string.text);
    }

    {
        paint.setTextSize(TEXT_SIZE);
        paint.getFontMetrics(fontMetrics);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 每行要显示的第一个字符
        int start = 0;

        // 当前行的宽度
        int totalWidth;

        // 文字上边缘，要想文字显示，就必须向下移文字的基线到上边缘的距离（文字高度）
        float y = -fontMetrics.top;

        // 获取屏幕第一行能放下的字符个数
        int count = paint.breakText(text, 0, text.length(), true, getWidth(), measuredTextWidth);

        while (count > 0) {
            canvas.drawText(text, start, start+count, 0, y, paint);
            start += count;
            // 文字高度 + 字符行间距
            y += paint.getFontSpacing();
            // 判断文字的上边缘与下边缘是否跟图片同一行
            if (y + fontMetrics.top > IMAGE_Y_OFFSET && y + fontMetrics.top < IMAGE_Y_OFFSET + IMAGE_WIDTH
            || y + fontMetrics.bottom > IMAGE_Y_OFFSET && y + fontMetrics.bottom < IMAGE_Y_OFFSET + IMAGE_WIDTH) {
                totalWidth = getWidth() - IMAGE_WIDTH;
            } else {
                totalWidth = getWidth();
            }
            // 获取屏幕当前行能放下的字符个数
            count = paint.breakText(text, start, text.length(), true, totalWidth, measuredTextWidth);
        }

        canvas.drawBitmap(getAvatar(IMAGE_WIDTH), getWidth() - IMAGE_WIDTH, IMAGE_Y_OFFSET, paint);
    }

    Bitmap getAvatar(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.icon, options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        return BitmapFactory.decodeResource(getResources(), R.mipmap.icon, options);
    }
}
