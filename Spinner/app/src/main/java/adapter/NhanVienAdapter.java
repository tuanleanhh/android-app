package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.spinner.MainActivity;
import com.example.spinner.R;

import java.util.ArrayList;
import java.util.List;

import Model.NhanVien;

public class NhanVienAdapter extends ArrayAdapter<NhanVien> {

    Activity context;
    int resource;
    List<NhanVien> objects;

    public NhanVienAdapter(@NonNull Activity context, int resource,
                           @NonNull List<NhanVien> objects) {
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
        TextView txtName = row.findViewById(R.id.txtName);
        TextView txtDay = row.findViewById(R.id.txtDay);
        Spinner spDay = row.findViewById(R.id.spDay);
        Button btnConfirm = row.findViewById(R.id.btnConfirm);


        NhanVien nv = this.objects.get(position);

        return row;
    }
}

