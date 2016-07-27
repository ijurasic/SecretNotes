package utils;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 7/27/16.
 */
public class Notes {

    public static void insertNote(String title, String note) {

        ContentValues values = new ContentValues();

        values.put("user_id", DButil.user_id);
        values.put("title", title);
        values.put("note", note);

        DButil.dbSecretNotes.insert("notes", null, values);
    }

    public static List<String> getAllNotes() {
        List<String> notesList = new ArrayList<String>();


        Cursor cursor = DButil.dbSecretNotes.rawQuery("select title from notes where user_id=1", null);

        try {
            while (cursor.moveToNext()) {
                String title = cursor.getString(0);

                notesList.add(title);
            }
        } finally {
            cursor.close();
        }


        return notesList;
    }
}
