package com.mrlee.contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnA, btnD, btnS;

    Button btnLastSelected=null;
    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        //Danh dau lai cac view
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLastSelected = btnA;
            }
        });
        btnA.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                btnLastSelected = btnA;
                return false;
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLastSelected = btnD;
            }
        });
        btnD.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                btnLastSelected = btnD;
                return false;
            }
        });
        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLastSelected = btnS;
            }
        });
        btnS.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                btnLastSelected = btnS;
                return false;
            }
        });

    }

    private void addControls() {
        btnA = findViewById(R.id.btnA);
        btnD = findViewById(R.id.btnD);
        btnS = findViewById(R.id.btnS);

        registerForContextMenu(btnA);
        registerForContextMenu(btnD);
        registerForContextMenu(btnS);
    }

    //khoi tao nap file xml
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu_main, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuInDam) {
                btnLastSelected.setTextColor(Color.RED);

        } else if (item.getItemId() == R.id.mnuRed) {

                btnLastSelected.setTypeface(typeface,Typeface.BOLD);
        } else if (item.getItemId() == R.id.mnuDel) {
            btnA.setVisibility(View.INVISIBLE);

        }
        return super.onContextItemSelected(item);
    }
}
