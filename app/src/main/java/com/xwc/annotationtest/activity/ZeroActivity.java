package com.xwc.annotationtest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import com.xwc.annotationtest.R;
import com.xwc.annotationtest.annotation.Click;
import com.xwc.annotationtest.annotation.ClickUtil;

public class ZeroActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zero);

        ClickUtil.bind(this);
    }

    @Click(R.id.tv_next)
    public void onClick(View view) {
        Intent intent = new Intent(this, FirstActivity.class);
        startActivity(intent);
    }

}
