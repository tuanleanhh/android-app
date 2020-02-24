package com.mrlee.shootingbaloon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import adapter.ScoreAdapter;
import data.DBManager;
import model.Score;

public class AddHighScoreActivity extends AppCompatActivity {

    String DATABASE_NAME = "dbScore.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/"; // tao ten thu muc luu CSDL
    SQLiteDatabase database = null;

    ListView lvScore;
    ArrayList<Score> listScore;

    ScoreAdapter scoreAdapter;

    Button btnSave;
    EditText txtTen;
    TextView txtDiem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        xuLySaoChepCSDLTuAssetVaoMobile();
                addControls();
                addEvents();
    }

    private void addEvents() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveHighScore();
            }
        });

    }

    private void showAllContactOnListView() {

        database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor = database.query("Score",null,null,null,
                null,null,null); //truyen null de truy vaan toan bo

        listScore.clear();
        while (cursor.moveToNext()){
            String ten = cursor.getString(1);
            String score = cursor.getString(2);
            Score scores = new Score(score,ten);
            listScore.add(scores);

        }
        cursor.close();
        scoreAdapter.notifyDataSetChanged();
    }
    //Xu Ly Luu Diem
    private void saveHighScore() {
        Intent intent = getIntent();
        txtTen = findViewById(R.id.txtTen);
        String s = intent.getStringExtra("Score");
        String ss = txtTen.getText().toString();
        ContentValues row = new ContentValues();
        row.put("Name",ss);
        row.put("Score",s);

        database.insert("Score",null,row); // long r = ... ko can thiet

        showAllContactOnListView();

    }

    private void addControls() {
        Intent intent = getIntent();

        btnSave = findViewById(R.id.btnSave);
        txtTen = findViewById(R.id.txtTen);
        txtDiem = findViewById(R.id.txtDiem);
        txtDiem.setText("Your Score: "+intent.getStringExtra("Score"));

        lvScore =findViewById(R.id.lvScore);
        listScore=new ArrayList<>();
        scoreAdapter = new ScoreAdapter(AddHighScoreActivity.this,R.layout.higscore,listScore);
        lvScore.setAdapter(scoreAdapter);
    }

    private void xuLySaoChepCSDLTuAssetVaoMobile() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if(!dbFile.exists()){
            try {
                CopyDataBaseFromAsset();
                Toast.makeText(this,"Sao chep thanh cong!",Toast.LENGTH_LONG).show();
            }catch (Exception e){
                Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
            }
        }

    }
    private void CopyDataBaseFromAsset() {

        try {
            InputStream inputStream = getAssets().open(DATABASE_NAME);
            String outFileName = layDuongDanLuuTru();
            File f = new File(getApplicationInfo().dataDir+DB_PATH_SUFFIX);
            if(!f.exists()){
                f.mkdir(); // neu khong ton tai thi tao duong dan thu muc
            }
            //tao file
            OutputStream outputStream = new FileOutputStream(outFileName);
            //ghi file
            byte[] buffer = new byte[1024];
            int lenght;
            while ((lenght= inputStream.read(buffer)) > 0) //inputStream.Read(buffer) : ghi dl vao buffer
            {
                outputStream.write(buffer,0,lenght);

            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();

        }catch (Exception ex){
            Log.e("Loi_SaoChep",ex.toString());
        }

    }

    private String layDuongDanLuuTru(){
        return getApplicationInfo().dataDir+DB_PATH_SUFFIX+DATABASE_NAME;
    }


    }


