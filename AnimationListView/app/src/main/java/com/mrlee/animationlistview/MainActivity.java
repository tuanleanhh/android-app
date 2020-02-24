package com.mrlee.animationlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import adapter.AdapterData;

public class MainActivity extends AppCompatActivity {
    ListView lvData;
    ArrayList<String>listData;
    ArrayAdapter<String>adapterData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();

    }

    private void addEvents() {
    }

    private void addControls() {
        lvData =findViewById(R.id.lvData);
        listData = new ArrayList<>();
        adapterData = new AdapterData(MainActivity.this,android.R.layout.simple_list_item_1,listData);
        lvData.setAdapter(adapterData);
        for (int i = 0; i < 5000; i++) {
            listData.add("Ten thu "+i);

        }
        adapterData.notifyDataSetChanged();
    }
}
