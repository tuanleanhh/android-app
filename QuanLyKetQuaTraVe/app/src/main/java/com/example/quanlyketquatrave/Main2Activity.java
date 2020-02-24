package com.example.quanlyketquatrave;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.xml.transform.Templates;

public class Main2Activity extends AppCompatActivity {
    TextView txtNhan;
    Button btnCal;
    Intent intent;
    int a,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinhUCLN();
            }
        });

    }

    private void tinhUCLN() {
        int UCLN=1;
        int min = Math.min(a,b);
        for (int i = min; i > 1; i--) {
            if((a%i==0) && (b%i==0)){
                UCLN=i;
                break;
            }
        }
        //B3: thay doi thong tin va gan vao cho intent
        //nen su dung lai intent ko nen tao moi
        intent.putExtra("UCLN",UCLN);
        //B4: danh dau ket qua tra ve
        setResult(33,intent);
        //B5: phai dong man hinh nay lai de MainActivity tro thanh foreground lifetime
        // vi no chi tu dong nhan dc ket qua tra ve khi MainActivity la FL
        finish();
    }

    private void addControls() {
        txtNhan = findViewById(R.id.txtNhan);
        btnCal = findViewById(R.id.btnCal);
        //B2 Lay du lieu
        intent=getIntent();
        a=intent.getIntExtra("a",-1);
        b=intent.getIntExtra("b",-1);
        txtNhan.setText("A = "+a+"   ; B = "+b);
    }
}
