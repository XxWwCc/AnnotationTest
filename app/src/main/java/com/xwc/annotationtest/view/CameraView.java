package com.xwc.annotationtest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.xwc.annotationtest.R;

public class CameraView extends View {

    private static final float PADDING = Utils.dp2px(50);
    private static final float IMAGE_WIDTH = Utils.dp2px(100);

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Camera camera = new Camera();

    float topFlip = 0;
    float bottomFlip = 0;
    float flipRotation = 0;

    public CameraView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        camera.setLocation(0, 0, Utils.getZForCamera());
    }

    public float getTopFlip() {
        return topFlip;
    }

    public void setTopFlip(float topFlip) {
        this.topFlip = topFlip;
        invalidate();
    }

    public float getBottomFlip() {
        return bottomFlip;
    }

    public void setBottomFlip(float bottomFlip) {
        this.bottomFlip = bottomFlip;
        invalidate();
    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.translate(PADDING + IMAGE_WIDTH/2, PADDING + IMAGE_WIDTH/2);
        canvas.rotate(-flipRotation);
        camera.save();
        camera.rotateX(topFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-IMAGE_WIDTH, -IMAGE_WIDTH, IMAGE_WIDTH, 0);
        canvas.rotate(flipRotation);
        canvas.translate(-(PADDING + IMAGE_WIDTH/2), -(PADDING + IMAGE_WIDTH/2));
        canvas.drawBitmap(getAvatar((int) IMAGE_WIDTH), PADDING, PADDING, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(PADDING + IMAGE_WIDTH/2, PADDING + IMAGE_WIDTH/2);
        canvas.rotate(-flipRotation);
        camera.save();
        camera.rotateX(bottomFlip);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-IMAGE_WIDTH, 0, IMAGE_WIDTH, IMAGE_WIDTH);
        canvas.rotate(flipRotation);
        canvas.translate(-(PADDING + IMAGE_WIDTH/2), -(PADDING + IMAGE_WIDTH/2));
        canvas.drawBitmap(getAvatar((int) IMAGE_WIDTH), PADDING, PADDING, paint);
        canvas.restore();
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
