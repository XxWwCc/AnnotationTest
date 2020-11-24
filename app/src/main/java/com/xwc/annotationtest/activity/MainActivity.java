package com.xwc.annotationtest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xwc.annotationtest.R;
import com.xwc.annotationtest.bean.UserParcelable;
import com.xwc.annotationtest.bean.UserSerializable;
import com.xwc.annotationtest.annotation.Click;
import com.xwc.annotationtest.annotation.ClickUtil;
import com.xwc.annotationtest.annotation.Smoker;
import com.xwc.annotationtest.annotation.SmokerUtil;
import com.xwc.annotationtest.view.SportsView;

public class MainActivity extends AppCompatActivity {

    @Smoker(R.id.tv)
    TextView textView;
    @Smoker(R.id.sportsView)
    SportsView sportsView;

    private UserParcelable userParcelable;
    private UserParcelable[] userParcelables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SmokerUtil.bind(this);
        textView.setText("仿写 ButtKnife 的 BindView 功能");

        ClickUtil.bind(this);

        userParcelable = new UserParcelable("0090900909", new String[]{"2343532", "23424454364", "234324234"}, 25);
        userParcelables = new UserParcelable[]{userParcelable, userParcelable};

        // 本质就是去重新设置 sweepAngle 的值
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(sportsView, "sweepAngle", 225);
        objectAnimator.setStartDelay(1000);
        objectAnimator.setDuration(2000);
        objectAnimator.start();

    }

    @Click({R.id.tv, R.id.tv_1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv:
                toSecond();
                break;
            case R.id.tv_1:
                Log.e("MainActivity", "====================tv_1");
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void toSecond() {
        Log.e("MainActivity", "====================tv");
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("name", "smoker");
        intent.putExtra("userParcelable", userParcelable);
        intent.putExtra("userParcelables", userParcelables);
        intent.putExtra("userSerializable", new UserSerializable("5645646545", new String[]{"44455", "524454"}, 18));
        intent.putExtra("id", 231326545);
        startActivity(intent);
    }
}