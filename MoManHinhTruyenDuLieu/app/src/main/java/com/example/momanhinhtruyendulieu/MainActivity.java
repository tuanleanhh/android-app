package com.example.momanhinhtruyendulieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import model.Student;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void moVaGuiDuLieuIntent(View view) {
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        intent.putExtra("boolean",true);
        intent.putExtra("char",'x');
        intent.putExtra("int",100);
        intent.putExtra("double",15.99);
        intent.putExtra("string","Lee");

        Student student = new Student(1001,"Justine Lee");
        intent.putExtra("Student",student);

        startActivity(intent);
    }
}
