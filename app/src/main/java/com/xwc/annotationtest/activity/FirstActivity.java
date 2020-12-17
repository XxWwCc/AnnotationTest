package com.xwc.annotationtest.activity;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xwc.annotationtest.R;
import com.xwc.annotationtest.annotation.Click;
import com.xwc.annotationtest.annotation.ClickUtil;
import com.xwc.annotationtest.annotation.Smoker;
import com.xwc.annotationtest.annotation.SmokerUtil;
import com.xwc.annotationtest.view.CameraView;
import com.xwc.annotationtest.view.PointView;
import com.xwc.annotationtest.view.Utils;

public class FirstActivity extends AppCompatActivity {

    @Smoker(R.id.camera_view)
    CameraView cameraView;
    @Smoker(R.id.camera_view_1)
    CameraView cameraView1;
    @Smoker(R.id.image_view)
    ImageView imageView;
    @Smoker(R.id.tv_next)
    TextView tvNext;
    @Smoker(R.id.point_view)
    PointView pointView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        SmokerUtil.bind(this);
        ClickUtil.bind(this);

        // 文字
        tvNext.animate().translationX(500)
                .translationY(1000)
                .setStartDelay(1000)
                .setDuration(2000)
                .start();
        // 图一
        ObjectAnimator bottomFlipAnimator = ObjectAnimator.ofFloat(cameraView, "bottomFlip", 45);
        bottomFlipAnimator.setDuration(1500);

        ObjectAnimator flipRotationAnimator = ObjectAnimator.ofFloat(cameraView, "flipRotation", 270);
        flipRotationAnimator.setDuration(1500);

        ObjectAnimator topFlipAnimator = ObjectAnimator.ofFloat(cameraView, "topFlip", -45);
        topFlipAnimator.setDuration(1500);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(bottomFlipAnimator, flipRotationAnimator, topFlipAnimator);
        animatorSet.setStartDelay(1000);
        animatorSet.start();

        // 图二
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("bottomFlip", 45);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("flipRotation", 270);
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("topFlip", -45);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(cameraView1, holder1, holder2, holder3);
        objectAnimator.setStartDelay(1000);
        objectAnimator.setDuration(2000);
        objectAnimator.start();

        // 图三
        float length = Utils.dp2px(200);
        Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
        Keyframe keyframe2 = Keyframe.ofFloat(0.2f, 1.2f*length);
        Keyframe keyframe3 = Keyframe.ofFloat(0.4f, 0.4f*length);
        Keyframe keyframe4 = Keyframe.ofFloat(0.6f, -0.2f*length);
        Keyframe keyframe5 = Keyframe.ofFloat(0.8f, 1.5f*length);
        Keyframe keyframe6 = Keyframe.ofFloat(1, 1*length);
        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("translationX", keyframe1, keyframe2, keyframe3, keyframe4, keyframe5, keyframe6);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageView, holder);
        animator.setStartDelay(1000);
        animator.setDuration(2000);
        animator.start();

        // TypeEvaluator
        Point point = new Point((int) Utils.dp2px(100), (int) Utils.dp2px(100));
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofObject(pointView, "point", new PointTypeEvaluator(), point);
        objectAnimator1.setStartDelay(1000);
        objectAnimator1.setDuration(2000);
        objectAnimator1.start();
    }

    @Click({R.id.tv_next})
    public void onClick(View view) {
        Intent intent = new Intent(FirstActivity.this, MainActivity.class);
        startActivity(intent);
    }

    class PointTypeEvaluator implements TypeEvaluator<Point> {
        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            int x = (int) (startValue.x + (endValue.x - startValue.x) * fraction);
            int y = (int) (startValue.y + (endValue.y - startValue.y) * fraction);
            return new Point(x, y);
        }
    }

}
