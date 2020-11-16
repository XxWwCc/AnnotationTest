package com.xwc.annotationtest;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xwc.annotationtest.annotation.IntentAnnotation;
import com.xwc.annotationtest.annotation.IntentUtil;
import com.xwc.annotationtest.annotation.Smoker;
import com.xwc.annotationtest.annotation.SmokerUtil;

public class SecondActivity extends AppCompatActivity {

    @IntentAnnotation("")
    private String name;

    @IntentAnnotation("")
    private UserParcelable userParcelable = new UserParcelable();

    @IntentAnnotation("")
    private UserParcelable[] userParcelables = new UserParcelable[]{};

    @IntentAnnotation("")
    private UserSerializable userSerializable = new UserSerializable();

    @IntentAnnotation("")
    private int id;

    @Smoker(R.id.tv)
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SmokerUtil.bind(this);
        IntentUtil.bind(this);

        textView.setText("name:" + name + ",\nid:" + id + ",\nuserParcelable:" + userParcelable + ",\nuserParcelables[0]:" + userParcelables + ",\nuserSerializable:" + userSerializable);
    }
}
