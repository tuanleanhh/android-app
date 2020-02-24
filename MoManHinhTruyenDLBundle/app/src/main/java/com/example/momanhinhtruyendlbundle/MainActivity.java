package com.example.momanhinhtruyendlbundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import model.Products;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void moVaGuiDuLieu(View view) {
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("x",453);
        bundle.putString("string","Don Hang");
        Products products = new Products(1001,"Iphone X",1300.5);
        bundle.putSerializable("Products",products);

        intent.putExtra("Bundle",bundle);

        startActivity(intent);

    }
}
