package com.example.xulysukien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtA,txtB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls(); // tao ham de khoi tao control. control ta truoc event
        addEvents(); // tao ham de khoi tao su kien

    }

    private void addEvents() {
    }

    private void addControls() {
        txtA =findViewById(R.id.txtA);
        txtB =findViewById(R.id.txtB);
    }

    public void xuLyPhepCong(View v){
        int a = Integer.parseInt(txtA.getText().toString());
        int b = Integer.parseInt(txtB.getText().toString());
        int c = a+b;
        Toast.makeText(MainActivity.this,"Tong = "+c,Toast.LENGTH_LONG).show();
    }
}
