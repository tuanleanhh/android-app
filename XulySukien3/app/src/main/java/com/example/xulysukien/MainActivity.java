package com.example.xulysukien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    //Tao Controll roif moi add vao su kien

    EditText txtA,txtB;
    Button btnMinus;
    // onclick xml phai khai bao
    //anomouslistener se ko can phai khai bao
    //variable dung de chia se su kien
    //activity as listener: la activy cos the sinh su kien:implement them interface OnlongclickListener
    //Explicit class Listener: la lop tuong minh co kha nang sinh va chia se su kien dung doi voi du an lon.
    //View Subclassing: it su dung: VD doi man hinh khac

    Button btnMulti,btnDevide;
    Button btnAn;
    Button btnExit;

    View.OnClickListener suKienChiaSe=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControlls();
        addEvents();

    }

    private void addEvents() {
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyPhepTru();
            }
        });
        suKienChiaSe = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.btnMulti){
                    xuLyPhepNhan();
                }else if(v.getId()==R.id.btnDevide){
                    xuLyPhepChia();

                }

            }
        };
        btnMulti.setOnClickListener(suKienChiaSe);
        btnDevide.setOnClickListener(suKienChiaSe);

        btnAn.setOnLongClickListener(this);

        btnExit.setOnClickListener(new MyEvent());
    }

    private void xuLyPhepNhan() {
        int a = Integer.parseInt(txtA.getText().toString());
        int b = Integer.parseInt(txtB.getText().toString());
        int c = a*b;
        Toast.makeText(MainActivity.this,"Tich = "+c,Toast.LENGTH_LONG).show();
    }
    private void xuLyPhepChia() {
        int a = Integer.parseInt(txtA.getText().toString());
        int b = Integer.parseInt(txtB.getText().toString());
        int c = a/b;
        Toast.makeText(MainActivity.this,"Thuong = "+c,Toast.LENGTH_LONG).show();
    }

    private void xuLyPhepTru() {
        int a = Integer.parseInt(txtA.getText().toString());
        int b = Integer.parseInt(txtB.getText().toString());
        int c = a-b;
        Toast.makeText(MainActivity.this,"Hieu = "+c,Toast.LENGTH_LONG).show();
    }

    private void addControlls() {
        txtA=findViewById(R.id.txtA);
        txtB=findViewById(R.id.txtB);
        btnMinus = findViewById(R.id.btnMinus);
        btnMulti = findViewById(R.id.btnMulti);
        btnDevide = findViewById(R.id.btnDevide);
        btnAn=findViewById(R.id.btnAn);
        btnExit = findViewById(R.id.btnExit);
    }

    public void xuLyPhepCong (View v){
        int a = Integer.parseInt(txtA.getText().toString());
        int b = Integer.parseInt(txtB.getText().toString());
        int c = a+b;
        Toast.makeText(MainActivity.this,"Tong = "+c,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onLongClick(View v) {
        if(v.getId()==R.id.btnAn)
            btnAn.setVisibility(View.INVISIBLE);
        return false;
    }
    public class MyEvent implements View.OnClickListener, View.OnLongClickListener{

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.btnExit){
                finish();
            }

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
    public void doiManHinh (View v){
        Button btnMoi = new Button(MainActivity.this)
        {
            @Override
            public boolean performClick() {
                setContentView(R.layout.activity_main);

                addControlls();
                addEvents();

                return super.performClick();
            }
        };
        btnMoi.setText("Quay Ve");
        btnMoi.setWidth(200);
        btnMoi.setHeight(200);

        setContentView(btnMoi);
    }
}
