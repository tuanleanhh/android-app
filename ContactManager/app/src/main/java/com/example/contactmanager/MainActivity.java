package com.example.contactmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import adapter.ContactAdapater;
import model.Contact;

public class MainActivity extends AppCompatActivity {

    EditText txtTen,txtPhone;
    Button btnSave;

    ListView lvContact;
    ArrayList<Contact> arrContact;
    ContactAdapater contactAdapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
    }

    private void addEvent() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyLuu();
            }
        });
    }

    private void xuLyLuu() {
        Contact contact = new Contact(txtTen.getText().toString(),txtPhone.getText().toString());
        arrContact.add(contact);
        contactAdapater.notifyDataSetChanged();
    }

    private void addControl() {
        txtTen=findViewById(R.id.txtName);
        txtPhone=findViewById(R.id.txtPhoneNumber);
        btnSave =findViewById(R.id.btnSave);
        lvContact = findViewById(R.id.lvContact);
        arrContact = new ArrayList<>();
        contactAdapater = new ContactAdapater(MainActivity.this,R.layout.item,arrContact);
        lvContact.setAdapter(contactAdapater);
    }
}
