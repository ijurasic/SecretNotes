package dbutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ivan.secretnotes.MainActivity;

/**
 * Created by ivan on 7/25/16.
 */
public class DButil  extends SQLiteOpenHelper {
    private static String DB_NAME = "snotes.db";
    public SQLiteDatabase dbSecretNotes;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("POSITION", "DButil.onCreate");
        sqLiteDatabase.execSQL("create table users(id integer NOT NULL primary key autoincrement, username text NOT NULL, password text NOT NULL)");
        sqLiteDatabase.execSQL("create table notes(id integer NOT NULL primary key autoincrement,user_id integer NOT NULL, note text NOT NULL," +
                "FOREIGN KEY(user_id) REFERENCES users(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("POSITION", "DButil.onUpgrade");

    }

    public DButil(Context context) {

        super(context, DB_NAME, null, 1);
        Log.d("POSITION", "DButil.DButil");
        dbSecretNotes=this.getWritableDatabase();
        }

    }