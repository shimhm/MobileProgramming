package com.example.team_project;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

public class SleepActivity extends Activity {

    myDBHelper myHelper;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        myHelper = new myDBHelper(this);

        ImageView btn_back = (ImageView) findViewById(R.id.btn_back);

        final ImageView img_gyro = (ImageView) findViewById(R.id.sleep_gyro);
        final ImageView img_CDs = (ImageView) findViewById(R.id.sleep_CDs);

        final Button btn_sleep_start = (Button) findViewById(R.id.sleep_start);

        final TextView date = (TextView) findViewById(R.id.sleep_date);

        final Chronometer chrono = (Chronometer) findViewById(R.id.sleep_chronometer);

        chrono.stop();
        chrono.setBase(SystemClock.elapsedRealtime());

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

                    chrono.start();

                    //start_date 저장
                    String start = nowTime();

                    date.setText(start);

                } else {
                    btn_sleep_start.setText("수면시작");

                    chrono.stop();
                    chrono.setBase(SystemClock.elapsedRealtime());

                    String end = nowTime();
                    String start = date.getText().toString();
                    date.setText(end);

                    myHelper.insertData(start, end);
                    Toast.makeText(getApplicationContext(), "입력됨", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //현재 시간 구하기
    public String nowTime(){
        // 현재시간을 msec 으로 구한다.
        long now = System.currentTimeMillis();
        // 현재시간을 date 변수에 저장한다.
        Date date = new Date(now);
        // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // nowDate 변수에 값을 저장한다.
        String formatDate = sdfNow.format(date);

        return formatDate;
    }

    //시간차이 구하기
    public long diffTime(String start, String end){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date sDate = sdf.parse(start);
            Date eDate = sdf.parse(end);

            long diff = eDate.getTime() - sDate.getTime();
            long timediff = diff / 1000;
            return timediff;
        } catch (Exception e){
            return 0;
        }
    }
}
