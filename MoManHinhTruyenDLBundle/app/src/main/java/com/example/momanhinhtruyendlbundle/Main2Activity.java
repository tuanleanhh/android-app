package com.example.momanhinhtruyendlbundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
        TextView txtRes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addControls();
    }

    private void addControls() {
        txtRes = findViewById(R.id.txtRes);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle");

        txtRes.setText("X = "+bundle.getInt("x")+"\n"+
                "String: "+bundle.getString("string")+"\n"+
                "Product: "+bundle.getSerializable("Products"));
    }
}
