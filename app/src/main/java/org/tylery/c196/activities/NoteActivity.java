package org.tylery.c196.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.tylery.c196.R;
import org.tylery.c196.entities.NoteEntity;
import org.tylery.c196.viewmodel.NoteViewModel;

public class NoteActivity extends AppCompatActivity {
    public static final String EXTRA_NOTE_COURSE_ID =
            "org.tylery.c196.activities.NOTE_COURSE_ID";
    public static final String EXTRA_NOTE_COURSE_TITLE =
            "org.tylery.c196.activities.NOTE_COURSE_TITLE";
    public static final String EXTRA_NOTE_ID =
            "org.tylery.c196.activities.NOTE_ID";
    public static final String EXTRA_NOTE_TITLE =
            "org.tylery.c196.activities.NOTE_TITLE";
    public static final String EXTRA_NOTE_CONTENT =
            "org.tylery.c196.activities.NOTE_CONTENT";

    public static final int EDIT_NOTE_REQUEST = 2;

    private NoteViewModel noteViewModel;

    private int noteID;
    private int courseID;
    private TextView textViewTitle;
    private TextView textViewContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        setContentView(R.layout.activity_note);

        textViewTitle = findViewById(R.id.detailed_note_title);
        textViewContent = findViewById(R.id.detailed_note_content);

        Intent parentIntent = getIntent();
        courseID = parentIntent.getIntExtra(EXTRA_NOTE_COURSE_ID, -1);
        noteID = parentIntent.getIntExtra(EXTRA_NOTE_ID, -1);
        String noteTitle = parentIntent.getStringExtra(EXTRA_NOTE_TITLE);
        String courseTitle = parentIntent.getStringExtra(EXTRA_NOTE_COURSE_TITLE);

        setTitle(courseTitle + " | " + noteTitle);

        textViewTitle.setText(parentIntent.getStringExtra(EXTRA_NOTE_TITLE));
        textViewContent.setText(parentIntent.getStringExtra(EXTRA_NOTE_CONTENT));

        FloatingActionButton buttonEditNote = findViewById(R.id.btn_edit_note);
        buttonEditNote.setOnClickListener(v -> {
            Intent editNoteIntent = new Intent(NoteActivity.this, AddEditNoteActivity.class);
            editNoteIntent.putExtra(AddEditNoteActivity.EXTRA_NOTE_ID, noteID);
            editNoteIntent.putExtra(AddEditNoteActivity.EXTRA_COURSE_NOTE_TITLE, noteTitle);
            editNoteIntent.putExtra(AddEditNoteActivity.EXTRA_COURSE_NOTE_CONTENT, textViewContent.getText().toString());
            startActivityForResult(editNoteIntent, EDIT_NOTE_REQUEST);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            int noteID = data.getIntExtra(AddEditNoteActivity.EXTRA_NOTE_ID, -1);
            String noteName = data.getStringExtra(AddEditNoteActivity.EXTRA_COURSE_NOTE_TITLE);
            String noteContent = data.getStringExtra(AddEditNoteActivity.EXTRA_COURSE_NOTE_CONTENT);

            if(noteID == -1) {
                Toast.makeText(this, "Error, note not saved", Toast.LENGTH_SHORT).show();
                return;
            }

            textViewTitle.setText(noteName);
            textViewContent.setText(noteContent);

            NoteEntity noteEntity = new NoteEntity(courseID,
                    noteName, noteContent);

            noteEntity.setId(noteID);
            noteViewModel.update(noteEntity);

            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not updated", Toast.LENGTH_SHORT).show();
        }
    }
}
