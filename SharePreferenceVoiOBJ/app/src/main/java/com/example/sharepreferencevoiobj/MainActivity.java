package com.example.sharepreferencevoiobj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtUserName,txtPass;
    Button btnDangNhap,btnThoat;
    CheckBox chkSave;
    String login = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        txtUserName = findViewById(R.id.txtUserName);
        txtPass = findViewById(R.id.txtPass);

        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnThoat = findViewById(R.id.btnThoat);

        chkSave = findViewById(R.id.chkSave);
    }

    private void addEvents() {
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyLogin();
            }
        });
    }

    private void xuLyLogin() {
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        //luu trong onPause
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences(login, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Username",txtUserName.getText().toString());
        editor.putString("Pass",txtPass.getText().toString());
        editor.putBoolean("Save",chkSave.isChecked());
        editor.commit();
    }

    @Override
    protected void onResume() {
        // lay lai trong onResume
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(login, MODE_PRIVATE);
        String userName = sharedPreferences.getString("Username","");
        String password = sharedPreferences.getString("Pass","");
        Boolean save = sharedPreferences.getBoolean("Save",false);
        if(save==true){
            txtUserName.setText(userName);
            txtPass.setText(password);

        }else {
            txtUserName.setText("");
            txtPass.setText("");
        }
    }
}
