package utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

/**
 * Created by ivan on 7/25/16.
 */
public class DButil  extends SQLiteOpenHelper {
    private static String DB_NAME = "snotes.db";
    public static SQLiteDatabase dbSecretNotes;
    public static String username;
    public static Integer user_id;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("POSITION", "DButil.onCreate");
        sqLiteDatabase.execSQL("create table users(id integer NOT NULL primary key autoincrement, username text NOT NULL, password text NOT NULL)");
        sqLiteDatabase.execSQL("create table notes(id integer NOT NULL primary key autoincrement, user_id integer NOT NULL, title text NOT NULL, note text NOT NULL," +
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

        String hashPwd = Hasher.sha256HexHash(password);

        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", hashPwd);

        dbSecretNotes.insert("users", null, values);
    }

    public boolean loginUser(String username, String pwd) {
        boolean loginOk = false;
        String hashPwd = Hasher.sha256HexHash(pwd);

        String sql = "select id from users where username=? and password=? ";
        SQLiteStatement stmt = dbSecretNotes.compileStatement(sql);
        stmt.bindString(1, username);
        stmt.bindString(2, hashPwd);

        long ret_user_id = 0;
        try {
            ret_user_id = stmt.simpleQueryForLong();
        } catch (Exception e) {
            // if query doesn't return anything, we will catch an exception
        }

        if (ret_user_id > 0) {
            loginOk = true;

            /*set static public variables:
            * username
            * user_id
            * so every other class can access them*/
            this.username = username;
            user_id = Integer.valueOf((int) ret_user_id);
        } else {
            this.username = null;
            user_id = null;
        }

        return loginOk;
    }


    }