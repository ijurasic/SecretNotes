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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        List<String> notesList = Notes.getAllNotes();

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

    public void onBtnNewNoteClick(View view) {
        startActivity(new Intent(NotesActivity.this, CreateNoteActivity.class));
    }
}
