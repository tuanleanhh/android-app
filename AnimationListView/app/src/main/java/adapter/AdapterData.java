package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mrlee.animationlistview.R;

import java.util.List;

public class AdapterData extends ArrayAdapter {
    Activity context;
    int resource;
    List objects;
    public AdapterData(@NonNull Activity context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);
        TextView txtData = row.findViewById(R.id.txtData);
        txtData.setText(this.objects.get(position).toString());

        Animation animation = AnimationUtils.loadAnimation(this.context,R.anim.effect_listview);
        row.startAnimation(animation);
        return super.getView(position, convertView, parent);
    }
}
