package com.example.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CheckBox chkAndroid, chkWindows, chkIos;
    Button btnChon;
    TextView txtMonhoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addControls() {
        chkAndroid = findViewById(R.id.chkAndroid);
        chkWindows = findViewById(R.id.chkWindows);
        chkIos = findViewById(R.id.chkIos);
        btnChon = findViewById(R.id.btnChon);
        txtMonhoc = findViewById(R.id.txtMonhoc);
    }

    private void addEvents() {
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyChonMonHoc();
            }
        });
    }

    private void xuLyChonMonHoc() {
        String s = "";
        if (chkAndroid.isChecked()) {
            s += chkAndroid.getText().toString() + "\n";
        }
        if (chkWindows.isChecked()) {
            s += chkWindows.getText().toString() + "\n";
        }
        if (chkIos.isChecked()) {
            s += chkIos.getText().toString();
        }
        txtMonhoc.setText(s);
    }
}
