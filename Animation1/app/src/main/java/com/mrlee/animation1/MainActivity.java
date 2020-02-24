package com.mrlee.animation1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btnCtrl,btnScreen,btn3s,btnEffect;

    Animation animation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvent();
    }

    private void addEvent() {
        btnCtrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.xoaycontrol);
                btnCtrl.startAnimation(animation);
            }
        });
        btnScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.xoaymanhinh);
                LinearLayout linearLayout = findViewById(R.id.layoutScreen);
                linearLayout.startAnimation(animation);
            }
        });
        btn3s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.xoaynguoc);
                btn3s.startAnimation(animation);
                 //tat ung dung khi ket thuc animation
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        finish();

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
    }

    private void addControls() {
        btnCtrl = findViewById(R.id.btnCtrl);
        btnScreen = findViewById(R.id.btnScreen);
        btn3s = findViewById(R.id.btn3s);
        btnEffect= findViewById(R.id.btnEffect);
        // tao animation


    }
}
