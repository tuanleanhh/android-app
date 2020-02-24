package com.example.karaoke;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import adapter.MusicAdapter;
import model.Music;

public class MainActivity extends AppCompatActivity {

    ListView lvSongs;
    ArrayList<Music> listSongs;
    MusicAdapter arrayAdapterSongs;

    ListView lvLikedSongs;
    ArrayList<Music> listLikedsongs;
    MusicAdapter arrayAdapterLikedSongs;

    TabHost tabHost;

    String DATABASE_NAME = "ArirangSongList.sqlite";
    String DB_PATH_SUFFIX = "/database/";
    SQLiteDatabase database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addEvents();
        addControls();

        xuLySaoChepCSDLTuAssetVaoMobile();
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion)
            xuLySaoChepCSDLTuAssetVaoMobile();
    }

    private void addControls() {

        tabHost = findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("", getResources().getDrawable(R.drawable.songs));
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("", getResources().getDrawable(R.drawable.like));
        tabHost.addTab(tab2);


        lvSongs = findViewById(R.id.lvSongs);
        listSongs = new ArrayList<>();
        arrayAdapterSongs = new MusicAdapter(MainActivity.this, R.layout.item, listSongs);
        lvSongs.setAdapter(arrayAdapterSongs);

        lvLikedSongs = findViewById(R.id.lvLikedSong);
        listLikedsongs = new ArrayList<>();
        arrayAdapterLikedSongs = new MusicAdapter(MainActivity.this, R.layout.item, listLikedsongs);
        lvLikedSongs.setAdapter(arrayAdapterLikedSongs);

    }

    private void addEvents() {
        tabHost =findViewById(R.id.tabHost);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equalsIgnoreCase("t1")) {
                    xuLyHienThiBaiHat();
                } else if (tabId.equalsIgnoreCase("t2")) {
                    xuLyHienThiBaiHatYeuThich();
                }
            }
        });

    }

    private void xuLyHienThiBaiHatYeuThich() {

    }

    private void xuLyHienThiBaiHat() {
        database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor = database.query("SongList",null,null,null,
                null,null,null); //truyen null de truy vaan toan bo
        listSongs.clear();
        while (cursor.moveToNext()){
            String maBh=cursor.getString(0);
            String tenBh = cursor.getString(1);
            String singer = cursor.getString(3);
            int yeuThich = cursor.getInt(5);

            Music music = new Music();
            music.setIdSong(maBh);
            music.setNameSong(tenBh);
            music.setSinger(singer);
            music.setLike(yeuThich==1);
            listSongs.add(music);
        }
        cursor.close();
        arrayAdapterSongs.notifyDataSetChanged();

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
