package com.example.mycontact;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtPhone, txtMassage;
    Button btnDial, btnCall, btnMassage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvents();
    }

    private void addEvents() {
        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyQuaySo();
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyGoi();
            }
        });
        btnMassage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyGuiTinNhan();
            }
        });
    }

    private void xuLyGuiTinNhan() {
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
                    Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();

                }
            }

        },new IntentFilter(Intent.ACTION_SEND));
        // thong bao gui tin nhan
        sms.sendTextMessage(txtPhone.getText().toString(),null,txtMassage.getText().toString()
                ,pendingMsgSent,null);

    }

    private void xuLyGoi() {
        Uri uri = Uri.parse("tel:" + txtPhone.getText().toString()); //cu phap goi dien
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
        }
        startActivity(intent);

    }

    private void xuLyQuaySo() {
        Uri uri=Uri.parse("tel:"+txtPhone.getText().toString()); //cu phap goi dien
        Intent intent=new Intent(Intent.ACTION_DIAL);
        intent.setData(uri);
        startActivity(intent);
    }

    private void addControl() {
        txtPhone = findViewById(R.id.txtPhoneNumber);
        txtMassage=findViewById(R.id.txtMassage);
        btnCall = findViewById(R.id.btnCall);
        btnDial =findViewById(R.id.btnDial);
        btnMassage=findViewById(R.id.btnMassage);
    }
}
