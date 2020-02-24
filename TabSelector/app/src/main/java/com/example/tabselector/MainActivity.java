package com.example.tabselector;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText txtA, txtB;
    //Button btnAdd;

    ListView lvHistory;
    ArrayList<String> arrAdd;
    ArrayAdapter<String> adapterAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addEvents();
        addControls();
    }

    private void addControls() {
        //B1: Tao tabHost
        TabHost tabHost = findViewById(R.id.tabHost);
        tabHost.setup();
        //B2:tao tab dua vao tabHost
        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        //tab1.setIndicator("1.Phep cong");
        tab1.setIndicator("", getResources().getDrawable(R.drawable.h1));
        tab1.setContent(R.id.tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        //tab2.setIndicator("2.Lich Su");
        tab2.setIndicator("", getResources().getDrawable(R.drawable.h2));
        tab2.setContent(R.id.tab2);

        //Add tab vao TabHost
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);


        txtA = findViewById(R.id.txtA);
        txtB = findViewById(R.id.txtB);
        //btnAdd = findViewById(R.id.btnAdd);
        lvHistory = findViewById(R.id.lvHistory);
        arrAdd = new ArrayList<>();
        adapterAdd = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrAdd);
        lvHistory.setAdapter(adapterAdd);
    }

    private void addEvents() {
        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyEventCong();
            }
        });
    }

    private void xuLyEventCong() {
        int a = Integer.parseInt(txtA.getText().toString());
        int b = Integer.parseInt(txtB.getText().toString());

        String s = a + " + " + b + " = " + (a + b);
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
        arrAdd.add(s);
        adapterAdd.notifyDataSetChanged();
        txtA.setText("");
        txtB.setText("");
    }
}
