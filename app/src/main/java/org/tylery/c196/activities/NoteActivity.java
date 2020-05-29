package org.tylery.c196.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.tylery.c196.R;
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

    }

}
