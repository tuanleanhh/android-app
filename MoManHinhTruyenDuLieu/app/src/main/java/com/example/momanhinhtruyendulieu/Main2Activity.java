package com.example.momanhinhtruyendulieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import model.Student;

public class Main2Activity extends AppCompatActivity {
    TextView txtRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addControls();
    }

    private void addControls() {
        txtRes = findViewById(R.id.txtRes);
        Intent intent = getIntent();
        //doi so 1 la ten bien
        //doi so 2 la gia tri mac dinh neu khoong tim thay bien
        boolean kieuBool = intent.getBooleanExtra("boolean", false);
        char kieuChar = intent.getCharExtra("char", 'y');
        int kieuInt = intent.getIntExtra("int", 0);
        double kieuDouble = intent.getDoubleExtra("double", 0.0);
        String kieuString = intent.getStringExtra("string");
        Student student= (Student) intent.getSerializableExtra("Student");

        txtRes.setText("Kieu Boolean = " + kieuBool + "\n" +
                "Kieu Char = " + kieuChar + "\n" +
                "Kieu Int = " + kieuInt + "\n" +
                "Kieu Double = " + kieuDouble + "\n" +
                "Kieu String = " + kieuString + "\n"+
                "Kieu Object: "+student);

    }
}
