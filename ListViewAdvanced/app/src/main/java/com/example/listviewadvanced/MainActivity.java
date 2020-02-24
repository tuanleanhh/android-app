package com.example.listviewadvanced;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.adapter.DanhBaAdapter;
import com.example.model.DanhBa;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvDanhBa;
    ArrayList<DanhBa> listDanhBa;
    DanhBaAdapter danhBaAdapter;

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
        lvDanhBa=findViewById(R.id.lvDanhBa);

        listDanhBa=new ArrayList<>();
        listDanhBa.add(new DanhBa(1,"Le Tuan","0985485454"));
        listDanhBa.add(new DanhBa(1,"Le B","09854876754"));
        listDanhBa.add(new DanhBa(1,"Le C","0985485657"));

        danhBaAdapter = new DanhBaAdapter(MainActivity.this,R.layout.item,listDanhBa);
        lvDanhBa.setAdapter(danhBaAdapter);
    }
}
