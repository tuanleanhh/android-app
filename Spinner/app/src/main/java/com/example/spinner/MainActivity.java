package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import Model.NhanVien;
import adapter.NhanVienAdapter;

public class MainActivity extends AppCompatActivity {

    EditText txtName,txtDay;
    Button btnConfirm;

    ListView lvInfor;

    Spinner spDay;

    ArrayList<String> arrThu;
    ArrayAdapter<String>adapterThu;

    ArrayList<NhanVien> arrNV;
    NhanVienAdapter nhanVienAdapter;

    int lastSelected = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControlds();
        addEvent();
    }

    private void addControlds() {
        txtDay=findViewById(R.id.txtDay);
        txtName=findViewById(R.id.txtName);

        btnConfirm=findViewById(R.id.btnConfirm);

        lvInfor=findViewById(R.id.lvInfor);

        spDay=findViewById(R.id.spDay);


        arrThu= new ArrayList<>();
        arrThu.add("Monday");arrThu.add("Tuesday");arrThu.add("Wednesday");arrThu.add("Thursday");
        arrThu.add("Friday");arrThu.add("Saturday");arrThu.add("Sunday");

        adapterThu=new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,arrThu); //hien thi noi dung mac dinh cua spiner
        //hien thi nhung noi dung khi an vao spiner
        adapterThu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDay.setAdapter(adapterThu);

    }

    private void addEvent() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyXacNhanCongTac();
            }
        });
        spDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this,"Ban chon: "+arrThu.get(position),Toast.LENGTH_LONG).show();
                lastSelected=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void xuLyXacNhanCongTac() {
        if(lastSelected==-1){
            Toast.makeText(MainActivity.this,"Chua chon noi dung",Toast.LENGTH_LONG).show();
            return;
        }
//        NhanVien nv=new NhanVien();
//        nv.setName(txtName.getText().toString());
//        nv.setThuBatDauCongTac(arrThu.get(lastSelected));
//        nv.setSoNgayCT(Integer.parseInt(txtDay.getText().toString()));

        //Toast.makeText(MainActivity.this,nv.toString(),Toast.LENGTH_LONG).show();

        arrNV = new ArrayList<>();
        arrNV.add(new NhanVien(txtName.getText().toString(),arrThu.get(lastSelected).toString(),
                Integer.parseInt(txtDay.getText().toString())));
        nhanVienAdapter=new NhanVienAdapter(MainActivity.this,R.layout.item,arrNV);
        lvInfor.setAdapter(nhanVienAdapter);

    }
}
