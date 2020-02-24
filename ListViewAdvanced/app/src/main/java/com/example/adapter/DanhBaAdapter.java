package com.example.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.listviewadvanced.R;
import com.example.model.DanhBa;

import java.util.List;

public class DanhBaAdapter extends ArrayAdapter <DanhBa> {
    Activity context; // man hinh su dung layout
    int resource; // layout cho tung dong muon hien thi
    List<DanhBa> objects; //danh sach nguon du lieu muon hien thi
    public DanhBaAdapter(@NonNull Activity context, int resource, @NonNull List<DanhBa> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    //ham getview hieu chinh giao dien
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater(); //lop dung de build layout thanh code java
        View row = inflater.inflate(this.resource,null);

        TextView txtName=row.findViewById(R.id.txtName);
        TextView txtNumber=row.findViewById(R.id.txtNumber);
        ImageButton btnCall=row.findViewById(R.id.btnCall);
        ImageButton btnSms=row.findViewById(R.id.btnSms);
        ImageButton btnDetail=row.findViewById(R.id.btnDetail);

        final DanhBa danhBa = this.objects.get(position);// tra ve danh ba hien tai
        txtName.setText(danhBa.getName());
        txtNumber.setText(danhBa.getPhoneNumber());

        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyXemChiTiet(danhBa);
            }
        });

        return row;
    }

    private void xuLyXemChiTiet(DanhBa danhBa) {
        Toast.makeText(this.context,"Ban Chon: "+danhBa.getName(),Toast.LENGTH_LONG).show();
    }
}
