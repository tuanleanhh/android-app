package com.example.radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioButton radTuyetvoi, radBinhthuong, radHoite, radRatte;
    Button btnBinhchon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnBinhchon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xyLyBinhChon();
            }
        });
    }

    private void xyLyBinhChon() {
        String s = "";
        if (radTuyetvoi.isChecked()) {
            s = radTuyetvoi.getText().toString();
        } else if (radBinhthuong.isChecked()) {
            s = radBinhthuong.getText().toString();
        } else if (radHoite.isChecked()) {
            s = radHoite.getText().toString();
        } else if (radRatte.isChecked()) {
            s = radRatte.getText().toString();
        }
        Toast.makeText(MainActivity.this,"Ban chon: "+s,Toast.LENGTH_LONG).show();

    }

    private void addControls() {
        radTuyetvoi = findViewById(R.id.radTuyetvoi);
        radBinhthuong = findViewById(R.id.radBinhthuong);
        radHoite = findViewById(R.id.radHoite);
        radRatte = findViewById(R.id.radRatte);
        btnBinhchon = findViewById(R.id.btnBinhChon);
    }
}
