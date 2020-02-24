package com.mrlee.shootingbaloon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import adapter.ScoreAdapter;
import model.Score;

public class HighScore extends AppCompatActivity {
    ListView lvDiemCao;
    ScoreAdapter scoreAdapter;
    ArrayList<Score> listDiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score2);
        addControls();

    }

    private void addControls() {
        lvDiemCao = findViewById(R.id.lvDiemCao);
        listDiem = new ArrayList<>();
        scoreAdapter = new ScoreAdapter(HighScore.this,R.layout.higscore,listDiem);
        lvDiemCao.setAdapter(scoreAdapter);
    }
}
