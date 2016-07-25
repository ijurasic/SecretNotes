package dbutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ivan.secretnotes.MainActivity;

/**
 * Created by ivan on 7/25/16.
 */
public class DButil  extends SQLiteOpenHelper {
    private static String DB_NAME = "snotes.db";
    public SQLiteDatabase dbSecretNotes;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table notes(id integer primary key autoincrement, note text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public DButil(Context context) {
        super(context, DB_NAME, null, 1);
        dbSecretNotes=this.getWritableDatabase();
        }

    }