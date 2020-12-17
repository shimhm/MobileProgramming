package com.example.team_project;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecordActivity extends Activity {

    myDBHelper myHelper;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        myHelper = new myDBHelper(this);

        BarChart chart = (BarChart) findViewById(R.id.record_chart);

        ImageView btn_back = (ImageView) findViewById(R.id.btn_back);

        drawChart(chart);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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

    public void drawChart(BarChart chart){
        sqlDB = myHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("select * from userSleep;", null);

        final ArrayList arrDate = new ArrayList();
        int i=0;
        while (cursor.moveToNext()) {
            String start = cursor.getString(0);
            String end = cursor.getString(1);

            long diff = diffTime(start, end);
            arrDate.add(new BarEntry(diff, i));
            i++;
        }

        cursor.close();
        sqlDB.close();

        ArrayList day = new ArrayList();

        for (int j = 1; j <= 10; j++) {
            day.add("i");
        }

        BarDataSet bardataset = new BarDataSet(arrDate, "time");
        chart.animateY(5000);
        BarData data = new BarData(day, bardataset);      // MPAndroidChart v3.X 오류 발생
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);
    }
}
