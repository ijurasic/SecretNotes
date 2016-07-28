package com.example.ivan.secretnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import utils.DButil;
import utils.Notes;

import static android.widget.Toast.LENGTH_SHORT;

public class NotesActivity extends AppCompatActivity {
    public final static int REQUEST_CODE_CREATE_NEW_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        refreshNotesListView();
    }

    public void onBtnNewNoteClick(View view) {
        //startActivity(new Intent(NotesActivity.this, CreateNoteActivity.class));
        Intent i = new Intent(this, CreateNoteActivity.class);
        startActivityForResult(i, REQUEST_CODE_CREATE_NEW_ACTIVITY);
    }

    public void refreshNotesListView() {
        Notes.getAllNotes();

        List<String> notesList = Notes.getAllNoteTitles();

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, notesList);

        ListView listViewNotes = (ListView) findViewById(R.id.listView);
        listViewNotes.setAdapter(adapter);

        listViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(NotesActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_CREATE_NEW_ACTIVITY:
                refreshNotesListView();
        }
    }
}
