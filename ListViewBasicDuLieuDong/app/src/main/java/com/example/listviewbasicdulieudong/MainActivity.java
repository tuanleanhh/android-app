package com.example.listviewbasicdulieudong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String>arrName;
    ArrayAdapter<String>adapterName;
    ListView lvName;

    EditText txtName;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyLuu();
            }
        });
    }

    private void xuLyLuu() {
        String ten=txtName.getText().toString();
        arrName.add(ten); //them du lieu moi
        adapterName.notifyDataSetChanged(); // ra lenh cho listview cap nhat lai giao dien
        txtName.setText("");
        txtName.requestFocus(); //di chuyen con tro van ban den o nhap
    }

    private void addControls() {
        arrName=new ArrayList<String>();
        adapterName=new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrName);
        lvName=findViewById(R.id.lvName);
        lvName.setAdapter(adapterName);
        txtName.findViewById(R.id.txtName);
        btnSave=findViewById(R.id.btnSave);
    }
}
