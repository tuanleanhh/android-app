package com.example.assetvasharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    TextView txtFont;
    ListView lvFont;
    ArrayList<String> listFont;
    ArrayAdapter <String> adapterFont;

    String tenLuuTru = "Trang thai font!"; // ten file xml neu k mac dinh la ten man hinh

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvent();

    }

    private void addEvent() {
        lvFont.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                xuLyFont(position);
            }
        });
    }

    private void xuLyFont(int position) {
        // set font cho Textview
        Typeface typeface = Typeface.createFromAsset(getAssets(),"font/"+listFont.get(position));
        txtFont.setTypeface(typeface);

        SharedPreferences preferences = getSharedPreferences(tenLuuTru, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("FontChu","font/"+listFont.get(position));// danh dau luu font chu;
        editor.commit(); // xac nhan luu
    }

    private void addControls() {
        txtFont = findViewById(R.id.txtFont);
        lvFont=findViewById(R.id.lvFont);
        listFont = new ArrayList<>();
        adapterFont=new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,listFont);
        lvFont.setAdapter(adapterFont);
        AssetManager assetManager = getAssets();


        try {
            String[] arrFontName = assetManager.list("font");
            listFont.addAll(Arrays.asList(arrFontName)); // truyen tat ca ten font vao(dua mang ve collection)
            // layfont da luu
            SharedPreferences preferences = getSharedPreferences(tenLuuTru, MODE_PRIVATE);
            String font = preferences.getString("FontChu","");
            if(font.length()>0){
                Typeface typeface = Typeface.createFromAsset(getAssets(),font);
                txtFont.setTypeface(typeface);
            }
        } catch (IOException e) {
            Log.e("LOI_FONT",e.toString());
        }
    }
}
