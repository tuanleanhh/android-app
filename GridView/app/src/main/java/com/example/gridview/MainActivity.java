package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.GridView;

import java.util.ArrayList;

import adapter.ImageAdapter;

public class MainActivity extends AppCompatActivity {

    GridView gvImages;
    ArrayList<Integer>arrImages;
    ImageAdapter adapter;

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
        gvImages=findViewById(R.id.gvImages);
        arrImages=new ArrayList<>();
        arrImages.add(R.drawable.h1);arrImages.add(R.drawable.h2);arrImages.add(R.drawable.h3);
        arrImages.add(R.drawable.h4);arrImages.add(R.drawable.h5);arrImages.add(R.drawable.h6);
        arrImages.add(R.drawable.h7);arrImages.add(R.drawable.h8);
        adapter=new ImageAdapter(MainActivity.this,R.layout.item,arrImages);
        gvImages.setAdapter(adapter);


    }
}
