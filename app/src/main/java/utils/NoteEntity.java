package utils;

/**
 * Created by ivan on 7/27/16.
 */
public class NoteEntity {
    public int id;
    public String title;
    public String note;

    public NoteEntity(int id, String title, String note) {
        this.id = id;
        this.title = title;
        this.note = note;
    }
}
