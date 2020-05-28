package org.tylery.c196.activities;

import androidx.appcompat.app.AppCompatActivity;

import org.tylery.c196.viewmodel.NoteViewModel;

public class CourseNotesListActivity extends AppCompatActivity {
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    public static final String EXTRA_COURSE_ID = "org.tylery.c196.activities.courseID";

    private int courseID;

    private NoteViewModel noteViewModel;

}
