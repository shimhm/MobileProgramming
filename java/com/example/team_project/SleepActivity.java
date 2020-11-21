package com.example.team_project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class SleepActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        ImageView btn_back = (ImageView) findViewById(R.id.btn_back);

        final ImageView img_gyro = (ImageView) findViewById(R.id.sleep_gyro);
        final ImageView img_CDs = (ImageView) findViewById(R.id.sleep_CDs);

        final Button btn_sleep_start = (Button) findViewById(R.id.sleep_start);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //SleepActivity 열 때 MenuActivity에서 특정 값을 넘겨줘서 수면중인지 판단해야함.
        btn_sleep_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_sleep_start.getText().toString().equals("수면시작")){
                    btn_sleep_start.setText("수면종료");
                } else {
                    btn_sleep_start.setText("수면시작");
                }
            }
        });
    }
}
