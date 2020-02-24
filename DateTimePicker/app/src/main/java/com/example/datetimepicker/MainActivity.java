package com.example.datetimepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView txtDate,txtTime;
    ImageButton btnDate,btnTime;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addControls();
        addEvents();
    }

    private void addEvents() {
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyHienThiDate();
            }
        });
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyHienThiTime();
            }
        });
    }

    private void xuLyHienThiTime() {
        TimePickerDialog.OnTimeSetListener callBack = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
                calendar.set(Calendar.MINUTE,minute);
                txtTime.setText(simpleDateFormat1.format(calendar.getTime()));
            }
        };
        TimePickerDialog timePickerDialog =new TimePickerDialog(MainActivity.this,
                callBack,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),true);
        timePickerDialog.show();
    }

    private void xuLyHienThiDate() {
        //gan thoi gian nguoi dung thay doi
        DatePickerDialog.OnDateSetListener callBack= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                txtDate.setText(simpleDateFormat.format(calendar.getTime()));

            }
        };
        DatePickerDialog datePickerDialog=new DatePickerDialog(MainActivity.this,
                callBack,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void addControls() {
        txtDate=findViewById(R.id.txtDate);
        txtTime=findViewById(R.id.txtTime);
        btnTime=findViewById(R.id.btnTime);
        btnDate=findViewById(R.id.btnDate);

        calendar=Calendar.getInstance();
        txtDate.setText(simpleDateFormat.format(calendar.getTime()));
        txtTime.setText(simpleDateFormat1.format(calendar.getTime()));

    }
}
