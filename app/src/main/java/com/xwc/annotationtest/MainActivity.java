package com.xwc.annotationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.xwc.annotationtest.annotation.Smoker;
import com.xwc.annotationtest.annotation.SmokerUtil;

public class MainActivity extends AppCompatActivity {

    @Smoker(R.id.tv)
    TextView textView;

    private UserParcelable userParcelable;
    private UserParcelable[] userParcelables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SmokerUtil.bind(this);

        textView.setText("仿写 ButtKnife 的 BindView 功能");

        userParcelable = new UserParcelable("0090900909", new String[]{"2343532", "23424454364", "234324234"}, 25);
        userParcelables = new UserParcelable[]{userParcelable, userParcelable};

        textView.setOnClickListener(view -> {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("name", "smoker");
            intent.putExtra("userParcelable", userParcelable);
            intent.putExtra("userParcelables", userParcelables);
            intent.putExtra("userSerializable", new UserSerializable("5645646545", new String[]{"44455", "524454"}, 18));
            intent.putExtra("id", 231326545);
            startActivity(intent);
        });
    }
}