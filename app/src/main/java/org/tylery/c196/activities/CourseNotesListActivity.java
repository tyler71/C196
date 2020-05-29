package org.tylery.c196.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.tylery.c196.R;
import org.tylery.c196.adapters.NoteAdapter;
import org.tylery.c196.entities.NoteEntity;
import org.tylery.c196.viewmodel.NoteViewModel;

public class CourseNotesListActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    public static final String EXTRA_COURSE_ID = "org.tylery.c196.activities.COURSE_ID";
    public static final String EXTRA_COURSE_TITLE = "org.tylery.c196.activities.COURSE_TITLE";

    private int courseID;
    private String courseTitle;

    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        FloatingActionButton buttonAddNote = findViewById(R.id.btn_add_note);
        buttonAddNote.setOnClickListener(v -> {
            Intent addNoteIntent = new Intent(CourseNotesListActivity.this, AddEditNoteActivity.class);
            startActivityForResult(addNoteIntent, ADD_NOTE_REQUEST);
        });

        Intent loadNoteListIntent = getIntent();
        courseID = loadNoteListIntent.getIntExtra(EXTRA_COURSE_ID, -1);
        courseTitle = loadNoteListIntent.getStringExtra(EXTRA_COURSE_TITLE);

        setTitle(courseTitle + " Notes");

        RecyclerView recyclerView = findViewById(R.id.noteListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        noteViewModel.getCourseNotes(courseID).observe(this, noteEntities -> adapter.setNotes(noteEntities));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                NoteEntity deletedNote = adapter.getNoteAt(viewHolder.getAdapterPosition());
                noteViewModel.delete(deletedNote);
                Toast.makeText(CourseNotesListActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(noteEntity -> {
            Intent loadNoteIntent = new Intent(CourseNotesListActivity.this, NoteActivity.class);
            loadNoteIntent.putExtra(NoteActivity.EXTRA_NOTE_ID, noteEntity.getId());
            loadNoteIntent.putExtra(NoteActivity.EXTRA_NOTE_COURSE_ID, noteEntity.getCourseID());
            loadNoteIntent.putExtra(NoteActivity.EXTRA_NOTE_COURSE_TITLE, courseTitle);
            loadNoteIntent.putExtra(NoteActivity.EXTRA_NOTE_TITLE, noteEntity.getName());
            loadNoteIntent.putExtra(NoteActivity.EXTRA_NOTE_CONTENT, noteEntity.getContent());
            startActivity(loadNoteIntent);
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
//            TODO
//              Likely convert these to UTC datetime
            int courseID = getIntent().getIntExtra(EXTRA_COURSE_ID, -1);
            int noteID = data.getIntExtra(AddEditNoteActivity.EXTRA_NOTE_ID, -1);
            String noteName = data.getStringExtra(AddEditNoteActivity.EXTRA_COURSE_NOTE_TITLE);
            String noteContent = data.getStringExtra(AddEditNoteActivity.EXTRA_COURSE_NOTE_CONTENT);

            NoteEntity noteEntity = new NoteEntity(courseID, noteName, noteContent);
            noteViewModel.insert(noteEntity);

            Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not added", Toast.LENGTH_SHORT).show();
        }
    }
}
