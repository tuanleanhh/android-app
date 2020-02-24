package com.example.sqlitequanlydanhba;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String DATABASE_NAME = "dbContact.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/"; // tao ten thu muc luu CSDL
    SQLiteDatabase database = null;

    ListView lvDanhBa;
    ArrayList<String>dsdanhBa;
    ArrayAdapter adapterDanhBa;

    Button btnAdd,btnEdit,btnDel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xuLySaoChepCSDLTuAssetVaoMobile();
        addControl();
        addEvent();

        showAllContactOnListView();
    }

    private void showAllContactOnListView() {
        //B1: mo CSDL
        //c1
        database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        Cursor cursor = database.query("Contact",null,null,null,
                null,null,null); //truyen null de truy vaan toan bo
        //c2
        //Cursor cursor1 = database.rawQuery("select * from Contact",null);
        dsdanhBa.clear();
        while (cursor.moveToNext()){
            int ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            String phone = cursor.getString(2);
            dsdanhBa.add(ma+" - "+ten+"\n"+phone);

        }
        cursor.close();
        adapterDanhBa.notifyDataSetChanged();
    }

    private void addControl() {
        lvDanhBa = findViewById(R.id.lvDanhBa);
        dsdanhBa = new ArrayList<>();
        adapterDanhBa = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,dsdanhBa);
        lvDanhBa.setAdapter(adapterDanhBa);

        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        btnDel = findViewById(R.id.btnDel);
    }

    private void addEvent() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xyLyThemDanhBa();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLySua();
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyXoa();
            }
        });
    }

    private void xuLyXoa() {
        //database.delete("Contact",null,null); //xoa toan bo bang
        database.delete("Contact","Ma=?",new String[]{"1004"});
        showAllContactOnListView();
    }

    private void xuLySua() {
        ContentValues row = new ContentValues();
        row.put("Ten","Quoc Trung");
        row.put("Phone","0325863526");
        database.update("Contact",row,"Ma=?",new String[]{"1004"});
        showAllContactOnListView();
    }

    private void xyLyThemDanhBa() {
        ContentValues row = new ContentValues();
        row.put("Ma",1004);
        row.put("Ten","Hoang Vy");
        row.put("Phone","0965421352");
        long r = database.insert("Contact",null,row); // long r = ... ko can thiet
        Toast.makeText(MainActivity.this,"Added, KQ trang thai r = "+r,
                Toast.LENGTH_LONG).show();
        showAllContactOnListView();
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
