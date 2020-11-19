package com.xwc.annotationtest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.xwc.annotationtest.R;

/**
 * describe: 头像
 * author: xuweichao
 * date: 2020/9/16 17:02
 */
public class AvatarView extends View {
    private static final float PADDING = Utils.dp2px(5);
    private static final float WIDTH = Utils.dp2px(75);
    private static final float INDENT = Utils.dp2px(5);
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    RectF rectF = new RectF();
    RectF inRectF = new RectF();
    Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = getAvatar((int) WIDTH);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF.set(PADDING, PADDING, PADDING + WIDTH, PADDING + WIDTH);
        inRectF.set(PADDING+INDENT, PADDING+INDENT, PADDING + WIDTH-INDENT, PADDING + WIDTH-INDENT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(rectF, paint);
        // 离屏
        int saved = canvas.saveLayer(rectF, paint);
        canvas.drawOval(inRectF, paint);
        paint.setXfermode(xfermode);
        canvas.drawBitmap(bitmap, PADDING, PADDING, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(saved);
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
