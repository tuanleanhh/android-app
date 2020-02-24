package com.example.quanlyketquatrave;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText txtA,txtB;
    Button btnUCLN;
    TextView txtRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();

    }

    private void addEvents() {
        btnUCLN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyLayUCLN();
            }
        });
    }

    private void xuLyLayUCLN() {
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        intent.putExtra("a",Integer.parseInt(txtA.getText().toString()));
        intent.putExtra("b",Integer.parseInt(txtB.getText().toString()));
        //B1: Goi startActivityForResult
        startActivityForResult(intent,99);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==99&&resultCode==33){
            txtRes.setText("Result: "+data.getIntExtra("UCLN",1));
        }
    }

    private void addControls() {
        txtA=findViewById(R.id.txtA);
        txtB=findViewById(R.id.txtB);
        txtRes=findViewById(R.id.txtRes);
        btnUCLN=findViewById(R.id.btnUCLN);
    }
}
