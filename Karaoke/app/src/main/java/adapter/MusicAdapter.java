package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.karaoke.R;

import java.util.List;

import model.Music;

public class MusicAdapter extends ArrayAdapter<Music> {

    Activity context;
    int resource;
    List<Music> objects;
    public MusicAdapter(@NonNull Activity context, int resource, @NonNull List<Music> objects) {
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
        TextView txtId = row.findViewById(R.id.txtId);
        TextView txtSongs = row.findViewById(R.id.txtSongs);
        TextView txtSinger = row.findViewById(R.id.txtSinger);
        ImageButton btnLike = row.findViewById(R.id.btnLike);
        ImageButton btnDislike = row.findViewById(R.id.btnDislike);

        final Music music = this.objects.get(position);
        txtSongs.setText(music.getNameSong());
        txtId.setText(music.getIdSong());
        txtSinger.setText(music.getSinger());


        // An hien nut like va dislike
        if(music.isLike()){
            btnLike.setVisibility(View.INVISIBLE);
            btnDislike.setVisibility(View.VISIBLE);
        }else {
            btnLike.setVisibility(View.VISIBLE);
            btnDislike.setVisibility(View.INVISIBLE);
        }
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyThich(music);
            }
        });
        btnDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDislike(music);
            }

        });

        return row;
    }

    private void xuLyDislike(Music music) {
        music.setLike(false);
    }

    private void xuLyThich(Music music) {
        music.setLike(true);
    }
}
