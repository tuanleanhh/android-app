package com.example.autocompletetextview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText txtName;
    AutoCompleteTextView autoTxtProvines;
    Button btnConfirm;
    TextView txtInfor;

    String[] arrProvines;
    ArrayAdapter<String> arrayAdapterProvines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvent();
    }

    private void addEvent() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyXacNhan();
            }
        });
    }

    private void xuLyXacNhan() {
        String s = txtName.getText().toString()+"\n"+autoTxtProvines.getText().toString();
        txtInfor.setText(s);
    }

    private void addControls() {
        txtName = findViewById(R.id.txtName);
        txtInfor = findViewById(R.id.txtInfor);
        btnConfirm = findViewById(R.id.btnConfirm);
        autoTxtProvines = findViewById(R.id.autoTxtProvinces);
        arrProvines=getResources().getStringArray(R.array.arrProvinces);
        arrayAdapterProvines = new ArrayAdapter<String>(
                MainActivity.this,android.R.layout.simple_list_item_1,arrProvines
        );
        autoTxtProvines.setAdapter(arrayAdapterProvines);
    }
}
