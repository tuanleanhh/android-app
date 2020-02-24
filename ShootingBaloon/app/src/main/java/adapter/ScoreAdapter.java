package adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mrlee.shootingbaloon.R;

import java.util.List;

import model.Score;

public class ScoreAdapter extends ArrayAdapter<Score> {

    Activity context;
    int resource;
    List<Score> objects;

    public ScoreAdapter(@NonNull Activity context, int resource, @NonNull List<Score> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects =objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);
        TextView txtName = row.findViewById(R.id.txtTen);
        TextView txtScore = row.findViewById(R.id.txtScore);
        final Score score = new Score();
        txtName.setText(score.getPlayerName().toString());
        txtScore.setText(score.getScore().toString());
        return super.getView(position, convertView, parent);
    }
}

