package com.example.imagebuttonimageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    RadioButton radTelephone,radCalculator,radPub;
    ImageButton btnExit;
    ImageView imgHinh;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        addControls();
        addEvents();
    }

    private void addEvents() {
        radTelephone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if(b){
                    imgHinh.setImageResource(R.drawable.telephone);
                }
            }
        });
        radCalculator.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    imgHinh.setImageResource(R.drawable.caculator);
                }
            }
        });
        radPub.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    imgHinh.setImageResource(R.drawable.pub);
                }
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        radCalculator = findViewById(R.id.radCalculator);
        radPub=findViewById(R.id.radPub);
        radTelephone=findViewById(R.id.radTelephone);
        imgHinh =findViewById(R.id.imgHinh);
        btnExit=findViewById(R.id.btnThoat);

    }
}
