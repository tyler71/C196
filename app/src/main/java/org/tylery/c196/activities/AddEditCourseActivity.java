package org.tylery.c196.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.tylery.c196.R;

public class AddEditCourseActivity extends AppCompatActivity {
    public static final String EXTRA_COURSE_ID =
            "org.tylery.c196.activities.COURSE_ID";
    public static final String EXTRA_COURSE_TITLE =
            "org.tylery.c196.activities.COURSE_TITLE";
    public static final String EXTRA_COURSE_START_DATE =
            "org.tylery.c196.activities.COURSE_START_DATE";
    public static final String EXTRA_COURSE_END_DATE =
            "org.tylery.c196.activities.COURSE_END_DATE";
    public static final String EXTRA_COURSE_ALERT =
            "org.tylery.c196.activities.COURSE_ALERT";
    public static final String EXTRA_COURSE_STATUS =
            "org.tylery.c196.activities.COURSE_STATUS";
    public static final String EXTRA_COURSE_MENTOR_NAME =
            "org.tylery.c196.activities.COURSE_MENTOR_NAME";
    public static final String EXTRA_COURSE_MENTOR_PHONE =
            "org.tylery.c196.activities.COURSE_MENTOR_PHONE";
    public static final String EXTRA_COURSE_MENTOR_EMAIL =
            "org.tylery.c196.activities.COURSE_MENTOR_EMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
    }
}
