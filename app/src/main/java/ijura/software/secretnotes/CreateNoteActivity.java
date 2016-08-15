package ijura.software.secretnotes;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import utils.Notes;

public class CreateNoteActivity extends AppCompatActivity {

    public static Integer note_position = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        Button btnCreateNote = (Button) findViewById(ijura.software.secretnotes.R.id.btnCreateNote);
        Button btnDeleteNote = (Button) findViewById(ijura.software.secretnotes.R.id.btnDeleteNote);

        EditText editTextCaption = (EditText) findViewById(ijura.software.secretnotes.R.id.editTextTitle);
        EditText editTextNoteText = (EditText) findViewById(ijura.software.secretnotes.R.id.editTextNote);

        if (note_position == null) {
            //user clicked on create new note button
            btnCreateNote.setText("Create");
            btnDeleteNote.setVisibility(View.INVISIBLE);
        } else {
            //user clicked on existing note..

            editTextCaption = (EditText) findViewById(ijura.software.secretnotes.R.id.editTextTitle);
            editTextNoteText = (EditText) findViewById(ijura.software.secretnotes.R.id.editTextNote);

            editTextCaption.setText(Notes.notesList.get(note_position).title);
            editTextNoteText.setText(Notes.notesList.get(note_position).note);

            btnCreateNote.setText("Update");
            btnDeleteNote.setVisibility(View.VISIBLE);
        }
                /*this lines are necessary to show keyboard on the screen*/


        if (editTextCaption.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void onBtnCreateNoteClick(View view) {

        if (note_position == null) {
            //user is creating a new note
            String title = ((EditText) findViewById(ijura.software.secretnotes.R.id.editTextTitle)).getText().toString();
            String note = ((EditText) findViewById(ijura.software.secretnotes.R.id.editTextNote)).getText().toString();

            Notes.insertNote(title, note);
            Toast.makeText(view.getContext(),
                    "Note created.", Toast.LENGTH_SHORT)
                    .show();
            this.finish();
        } else {
            //user is saving an existing note
            String title = ((EditText) findViewById(ijura.software.secretnotes.R.id.editTextTitle)).getText().toString();
            String note = ((EditText) findViewById(ijura.software.secretnotes.R.id.editTextNote)).getText().toString();

            Notes.updateNote(Notes.notesList.get(note_position).id, title, note);
            Toast.makeText(view.getContext(),
                    "Note updated.", Toast.LENGTH_SHORT)
                    .show();
            this.finish();
        }

    }

    public void onBtnCancelCreateNote(View view) {
        this.finish();
    }

    public void closeActivity() {
        this.finish();
    }

    public void onBtnDeleteNote(final View view) {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        Notes.deleteNote(Notes.notesList.get(note_position).id);
                        Toast.makeText(view.getContext(),
                                "Note deleted.", Toast.LENGTH_SHORT)
                                .show();
                        closeActivity();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();


    }


}
