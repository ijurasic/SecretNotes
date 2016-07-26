package dbutils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        dbSecretNotes = this.getWritableDatabase();
    }

    public boolean userExists(String username) {

        boolean userExists = false;
        Cursor resultSet = dbSecretNotes.rawQuery("select username,password from users", null);

        if (resultSet.getCount() > 0) {
            userExists = true;
        }
       /* resultSet.moveToFirst();
        String username = resultSet.getString(1);
        String password = resultSet.getString(2);*/
        return userExists;
    }

    public void createUser(String username, String password) {
        //SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);

        dbSecretNotes.insert("users", null, values);
    }


    }