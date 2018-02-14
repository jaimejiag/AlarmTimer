package com.examle.jaime.alarmtimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final int ALARMTIMER = 1;

    private TimePicker timePicker;
    private FloatingActionButton fabTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        fabTimer = findViewById(R.id.fab_alarm);
        fabTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.examle.jaime.alarmtimer");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,
                        ALARMTIMER, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                calendar.set(Calendar.SECOND, 0);

                //Registro una alarma teniendo en cuenta el valor introducido en el TimePicker
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                finish();
            }
        });
    }
}
