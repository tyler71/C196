package org.tylery.c196.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.tylery.c196.R;

public class AssessmentActivity extends AppCompatActivity {
    public static final String EXTRA_ASSESSMENT_COURSE_ID =
            "org.tylery.c196.activities.ASSESSMENT_COURSE_ID";
    public static final String EXTRA_ASSESSMENT_COURSE_TITLE =
            "org.tylery.c196.activities.ASSESSMENT_COURSE_TITLE";
    public static final String EXTRA_ASSESSMENT_ID =
            "org.tylery.c196.activities.ASSESSMENT_ID";
    public static final String EXTRA_ASSESSMENT_TITLE =
            "org.tylery.c196.activities.ASSESSMENT_TITLE";
    public static final String EXTRA_ASSESSMENT_DUE_DATE =
            "org.tylery.c196.activities.ASSESSMENT_DUE_DATE";

//    TODO Move to AddEditAssessment
//    public static final int EDIT_NOTE_REQUEST = 2;

    private int courseID;
    private int assessmentID;
    private TextView textViewTitle;
    private TextView textView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
    }
}
