package com.mrlee.shootingbaloon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import model.Score;

public class MainActivity extends AppCompatActivity {

    int score;
    Random random;
    TextView txtScore;
    ViewGroup.LayoutParams layoutParams;
    LinearLayout layoutBaloon;
    Button btnCreat;
    ObjectAnimator objectAnimator;

    int lv = 3;

    public Score scoreO = new Score();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addEvent();
        addControls();
    }
    //Tao option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.level_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //xu ly lua chon
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.lv1) {
            lv = 3;
            Toast.makeText(this,"Bạn chọn level 1",Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.lv2) {
            lv = 5;
            Toast.makeText(this,"Bạn chọn level 2",Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.lv3) {
            lv = 10;
            Toast.makeText(this,"Bạn chọn level 3",Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.exit) {
            final AlertDialog.Builder alerDialog = new AlertDialog.Builder(this);
            alerDialog.setTitle("Thông báo!");
            alerDialog.setIcon(R.mipmap.ic_launcher);
            alerDialog.setMessage("Bạn có muốn thoát game không?");

            alerDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alerDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alerDialog.show();


        } else if (item.getItemId() == R.id.history) {
            Intent intent = new Intent(MainActivity.this, HighScore.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    private void addControls() {

        txtScore = findViewById(R.id.txtTen);
        layoutBaloon = findViewById(R.id.layoutBaloon);
        random = new Random();
        layoutParams = new ViewGroup.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
    }

    private void addEvent() {
        btnCreat = findViewById(R.id.btnCreat);
        btnCreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = 0;
                txtScore.setText("Score: " + score);
                for (int i = 0; i <= lv; i++) {
                    proceedAnimation();
                }
            }
        });
    }

    protected void proceedAnimation() {
        final ImageView img = getImageView();
        img.setBackground(getDrawable());
        img.setMaxWidth(2);
        img.setMaxHeight(4);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                img.setBackground(getResources().getDrawable(R.drawable.bang));
                img.setMaxWidth(2);
                img.setMaxHeight(4);
                layoutBaloon.removeView(arg0);
                score +=1;
                txtScore.setText("Score: " + (score));
            }
        });

        objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(MainActivity.this,
                R.animator.baloon_animation);
        objectAnimator.setDuration(random.nextInt(1000) + 2000);
        objectAnimator.setTarget(img);

        layoutBaloon.addView(img, layoutParams);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                layoutBaloon.removeView((View) ((ObjectAnimator) animation).getTarget());
                Toast.makeText(MainActivity.this, "Game Over! Your Score: " + score,
                        Toast.LENGTH_LONG).show();
                final AlertDialog.Builder alerDialog = new AlertDialog.Builder(MainActivity.this);
                alerDialog.setTitle("Thông báo!");
                alerDialog.setIcon(R.mipmap.ic_launcher);
                alerDialog.setMessage("Bạn có muốn lưu điểm không?");

                alerDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s = Integer.toString(score);
                        Intent intent = new Intent(MainActivity.this,AddHighScoreActivity.class);
                        intent.putExtra("Score",s);
                        startActivity(intent);
                    }
                });
                alerDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alerDialog.show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.start();


    }

    private Drawable getDrawable() {
        Drawable drawable;
        int i = random.nextInt(3);
        switch (i) {
            case 0:
                drawable = getResources().getDrawable(R.drawable.blue);
                break;
            case 1:
                drawable = getResources().getDrawable(R.drawable.orage);
                break;
            case 2:
                drawable = getResources().getDrawable(R.drawable.purple);
                break;
            case 3:
                drawable = getResources().getDrawable(R.drawable.red);
                break;
            default:
                drawable = getResources().getDrawable(R.drawable.blue);
                break;
        }
        return drawable;
    }

    public ImageView getImageView() {
        ImageView img = new ImageView(MainActivity.this);
        img.setX(random.nextInt(500));
        return img;
    }

}
