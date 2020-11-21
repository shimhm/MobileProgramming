package com.example.team_project;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class StudyActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        ImageView btn_back = (ImageView) findViewById(R.id.btn_back);

        final ImageView img_gyro = (ImageView) findViewById(R.id.study_gyro);
        final ImageView img_CDs = (ImageView) findViewById(R.id.study_CDs);
        final ImageView img_dist = (ImageView) findViewById(R.id.study_dist);

        final Button btn_study_start = (Button) findViewById(R.id.study_start);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        img_gyro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_gyro.setImageResource(R.drawable.good);
            }
        });

        //StudyActivity 열 때 MenuActivity에서 특정 값을 넘겨줘서 공부중인지 판단해야함.
        btn_study_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_study_start.getText().toString().equals("공부시작")){
                    btn_study_start.setText("공부종료");
                } else {
                    btn_study_start.setText("공부시작");
                }
            }
        });

    }
}
