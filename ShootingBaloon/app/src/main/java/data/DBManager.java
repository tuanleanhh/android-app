package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import model.Score;

public class DBManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "db_score";
    private static final String TABLE_NAME = "score";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SCORE = "score";
    public static int VERSION = 1;

    private Context context;

    private String SQLQuery = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " integer primary key autoincrement, " +
            NAME + " text, " +
            SCORE + " text)";

    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    //tao Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public void addScore(Score score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, score.getPlayerName());
        values.put(SCORE, score.getScore());
        //Neu de null thi khi value bang null thi loi

        db.insert(TABLE_NAME,null,values);

        db.close();
    }
    public ArrayList<Score> getScore() {
        ArrayList<Score> listScore = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Score score = new Score();
                score.setPlayerName(cursor.getString(1));
                score.setScore(cursor.getString(2));
                listScore.add(score);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listScore;
    }
}
