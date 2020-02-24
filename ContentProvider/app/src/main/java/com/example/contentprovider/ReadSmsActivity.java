package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ReadSmsActivity extends AppCompatActivity {
    ListView lvSms;
    ArrayList<String> listSms;
    ArrayAdapter<String> adapterSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_sms);

        addControls();
        addEvents();

        readSms();
    }

    private void readSms() {
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        listSms.clear();
        while (cursor.moveToNext()){
            int soCotPhone = cursor.getColumnIndex("address");
            int soCotTime = cursor.getColumnIndex("date");
            int soCotNoiDung = cursor.getColumnIndex("body");

            String phoneNumber = cursor.getString(soCotPhone);
            String timeStamp = cursor.getString(soCotTime);
            String content = cursor.getString(soCotNoiDung);

            listSms.add(phoneNumber+"\n"+timeStamp+"\n"+content);
        }
        cursor.close();
        adapterSms.notifyDataSetChanged();
    }

    private void addEvents() {
    }

    private void addControls() {
        lvSms = findViewById(R.id.lvSms);
        listSms = new ArrayList<>();
        adapterSms = new ArrayAdapter<String>(ReadSmsActivity.this,android.R.layout.simple_list_item_1,listSms);
        lvSms.setAdapter(adapterSms);
    }
}
