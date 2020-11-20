package com.example.team_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


//일단 메뉴 화면에서 각 메뉴로 접근
public class MainActivity extends AppCompatActivity {

    Integer[] menuIDs = {R.id.menu_study, R.id.menu_sleep, R.id.menu_record, R.id.menu_user};
    ImageView[] menu = new ImageView[menuIDs.length];

//    Intent[] intents = {new Intent(getApplicationContext(), StudyActivity.class), new Intent(getApplicationContext(), SleepActivity.class),
//            new Intent(getApplicationContext(), RecordActivity.class), new Intent(getApplicationContext(), UserActivity.class) };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        for(int i=0;i<menuIDs.length;i++){
            menu[i] = (ImageView) findViewById(menuIDs[i]);
        }

//        for(int i=0;i<menuIDs.length;i++){
//            final int index = i;
//            menu[index].setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    startActivity(intents[index]);
//                }
//            });
//        }
        menu[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StudyActivity.class);
                startActivity(intent);
            }
        });

        menu[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SleepActivity.class);
                startActivity(intent);
            }
        });

        menu[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RecordActivity.class);
                startActivity(intent);
            }
        });

        menu[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                startActivity(intent);
            }
        });
    }
}
