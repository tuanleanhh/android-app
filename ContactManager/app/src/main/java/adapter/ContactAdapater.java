package adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentContainer;

import com.example.contactmanager.MessageActivity;
import com.example.contactmanager.R;

import java.util.List;

import model.Contact;

public class ContactAdapater extends ArrayAdapter<Contact> {
    Activity context;
    int resource;
    List<Contact> objects;

    public ContactAdapater(@NonNull Activity context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);

        TextView txtTen = row.findViewById(R.id.txtName);
        TextView txtPhone = row.findViewById(R.id.txtPhoneNumber);
        ImageButton btnCall = row.findViewById(R.id.btnCall);
        ImageButton btnMessage = row.findViewById(R.id.btnMessage);
        ImageButton btnDel = row.findViewById(R.id.btnDel);

        final Contact contact = this.objects.get(position);
        txtTen.setText(contact.getName());
        txtPhone.setText(contact.getPhoneNumber());

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyCall(contact);
            }
        });
        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xyLyMessage(contact);
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDelete(contact);
            }
        });
        return row;
    }

    private void xuLyDelete(Contact contact) {
        this.remove(contact);

    }

    private void xyLyMessage(Contact contact) {
        Intent intent = new Intent(this.context, MessageActivity.class);
        intent.putExtra("contact",contact);
        this.context.startActivity(intent);
    }

    private void xuLyCall(Contact contact) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri uri = Uri.parse("tel:" + contact.getPhoneNumber().toString());
        intent.setData(uri);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this.context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                if(ActivityCompat.shouldShowRequestPermissionRationale(this.context,Manifest.permission.CALL_PHONE)){
                    Toast.makeText(this.context, "Permission Denied", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this.context,"Denied,don't show this again!",Toast.LENGTH_LONG).show();
                }
            }

        }else {
            Toast.makeText(this.context, "Permission granted!", Toast.LENGTH_LONG).show();
        }
        this.context.startActivity(intent);
    }
}
