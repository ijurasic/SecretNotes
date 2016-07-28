package utils;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.ivan.secretnotes.NotesActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 7/27/16.
 */
public class Notes {

    public static List<NoteEntity> notesList;

    public static void insertNote(String title, String note) {

        ContentValues values = new ContentValues();

        values.put("user_id", DButil.user_id);
        values.put("title", AES.encrypt(title));
        values.put("note", AES.encrypt(note));

        DButil.dbSecretNotes.insert("notes", null, values);
    }

    public static void getAllNotes() {
        notesList = new ArrayList<NoteEntity>();

        Cursor cursor = DButil.dbSecretNotes.rawQuery("select id, title, note from notes where user_id=?", new String[]{String.valueOf(DButil.user_id)});

        try {
            while (cursor.moveToNext()) {
                int note_id = cursor.getInt(0);
                String title = AES.decrypt(cursor.getString(1));
                String note = AES.decrypt(cursor.getString(2));
                notesList.add(new NoteEntity(note_id, title, note));
            }
        } finally {
            cursor.close();
        }

    }

    public static List<String> getAllNoteTitles() {
        List<String> lstNoteTitles = new ArrayList<String>();
        for (NoteEntity note : notesList) {
            lstNoteTitles.add(note.title);
        }

        return lstNoteTitles;
    }
}
