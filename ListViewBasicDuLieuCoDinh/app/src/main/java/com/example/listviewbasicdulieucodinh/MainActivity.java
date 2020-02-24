package com.example.listviewbasicdulieucodinh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView lvDays;
    String [] arrDays; //du lieu nguon
    ArrayAdapter<String>adapterDays; //nguon kieu gi adapter kieu do

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        lvDays.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Ban chon: "+arrDays[position],Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addControls() {
        arrDays=getResources().getStringArray(R.array.arrDays);
        adapterDays=new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrDays);
        lvDays=findViewById(R.id.lvDays);
        //b3 Gan adapter cho listview
        lvDays.setAdapter(adapterDays);

    }
}
