package org.tylery.c196.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import org.tylery.c196.R;
import org.tylery.c196.viewmodel.AssessmentViewModel;

public class CourseAssessmentsListActivity extends AppCompatActivity {
    public static final int ADD_ASSESSMENT_REQUEST = 1;

    public static final String EXTRA_COURSE_ID = "org.tylery.c196.activities.COURSE_ID";
    public static final String EXTRA_COURSE_TITLE = "org.tylery.c196.activities.COURSE_TITLE";

    private int courseID;
    private String courseTitle;

    private AssessmentViewModel assessmentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_assessments_list);

        Intent loadAssessmentsListIntent = getIntent();
        courseID = loadAssessmentsListIntent.getIntExtra(EXTRA_COURSE_ID, -1);
        courseTitle = loadAssessmentsListIntent.getStringExtra(EXTRA_COURSE_TITLE);

        setTitle(courseTitle + " Assessments");

//        RecyclerView recyclerView = findViewById(R.id.assessment)
    }
}
