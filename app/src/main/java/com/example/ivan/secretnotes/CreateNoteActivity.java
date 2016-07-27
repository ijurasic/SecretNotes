package com.example.ivan.secretnotes;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import utils.DButil;
import utils.Notes;

public class CreateNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
    }

    public void onBtnCreateNoteClick(View view) {


        String title = ((EditText) findViewById(R.id.editTextTitle)).getText().toString();
        String note = ((EditText) findViewById(R.id.editTextNote)).getText().toString();

        Notes.insertNote(title, note);


    }


}
