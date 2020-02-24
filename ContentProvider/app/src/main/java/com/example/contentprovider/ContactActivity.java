package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import model.Contact;

public class ContactActivity extends AppCompatActivity {
    ListView lvContact;
    ArrayList <Contact> listContact;
    ArrayAdapter<Contact> adapterContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        addControls();
        addEvents();

        showAllContactFromDevice();
    }

    private void showAllContactFromDevice() {
        Uri uriContact = ContactsContract.CommonDataKinds.Phone.CONTENT_URI; //truy xuat thong tin danh ba
        Cursor cursor = getContentResolver().query(uriContact,null,null,null
        ,null,null);
        listContact.clear();
        while (cursor.moveToNext()){
            String tenCotName = ContactsContract.Contacts.DISPLAY_NAME;
            String tenCotPhone = ContactsContract.CommonDataKinds.Phone.NUMBER;
            int vtTenCotName = cursor.getColumnIndex(tenCotName);
            int vtTenCotPhone = cursor.getColumnIndex(tenCotPhone);
            String name = cursor.getString(vtTenCotName);
            String phone = cursor.getString(vtTenCotPhone);
            Contact contact = new Contact(name,phone);
            listContact.add(contact);
        }
        adapterContact.notifyDataSetChanged();
    }

    private void addEvents() {
    }

    private void addControls() {
        lvContact = findViewById(R.id.lvContact);
        listContact =new ArrayList<>();
        adapterContact = new ArrayAdapter<Contact>(ContactActivity.this,android.R.layout.simple_list_item_1,listContact);
        lvContact.setAdapter(adapterContact);
    }
}
