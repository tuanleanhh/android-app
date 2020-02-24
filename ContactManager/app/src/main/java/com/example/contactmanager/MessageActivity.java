package com.example.contactmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import model.Contact;

public class MessageActivity extends AppCompatActivity {
    TextView txtReciever;
    EditText txtContent;
    ImageButton btnSend;
    Contact selectedContact = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        addControls();
        addEvent();
    }

    private void addEvent() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyGui();
            }
        });
    }

    private void xuLyGui() {
        final SmsManager sms = SmsManager.getDefault();
        Intent smsSent = new Intent(Intent.ACTION_SEND);
        final PendingIntent pendingMsgSent = PendingIntent.getBroadcast(this,0,smsSent,0);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int result = getResultCode();
                String msg = "Sent!";
                if(result != Activity.RESULT_OK){
                    msg = "Sent Failed";
                    Toast.makeText(MessageActivity.this,msg,Toast.LENGTH_LONG).show();

                }
            }

        },new IntentFilter(Intent.ACTION_SEND));
        // thong bao gui tin nhan
        sms.sendTextMessage(selectedContact.getPhoneNumber().toString(),null,txtContent.getText().toString()
                ,pendingMsgSent,null);

    }

    private void addControls() {
        txtReciever = findViewById(R.id.txtReciever);
        txtContent = findViewById(R.id.txtContent);
        btnSend = findViewById(R.id.btnSend);

        Intent intent = getIntent();
        selectedContact = (Contact) intent.getSerializableExtra("contact");
        txtReciever.setText(selectedContact.getPhoneNumber());
    }
}
