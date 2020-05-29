package org.tylery.c196.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.tylery.c196.R;
import org.tylery.c196.adapters.AssessmentAdapter;
import org.tylery.c196.viewmodel.AssessmentViewModel;

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
    public static final String EXTRA_ASSESSMENT_ALARM =
            "org.tylery.c196.activities.ASSESSMENT_ALARM";

//    TODO Move to AddEditAssessment
//    public static final int EDIT_NOTE_REQUEST = 2;

    private AssessmentViewModel assessmentViewModel;

    private int courseID;
    private String courseTitle;
    private int assessmentID;
    private TextView textViewTitle;
    private TextView textViewDueDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assessmentViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);
        setContentView(R.layout.activity_assessment);

        textViewTitle = findViewById(R.id.detailed_assessment_due_date);
        textViewDueDate = findViewById(R.id.detailed_assessment_due_date);

        Intent parentIntent = getIntent();
        courseID = parentIntent.getIntExtra(EXTRA_ASSESSMENT_COURSE_ID, -1);
        assessmentID = parentIntent.getIntExtra(EXTRA_ASSESSMENT_ID, -1);
        courseTitle = parentIntent.getStringExtra(EXTRA_ASSESSMENT_COURSE_TITLE);
        String assessmentTitle = parentIntent.getStringExtra(EXTRA_ASSESSMENT_TITLE);

        setTitle(courseTitle + " | " + assessmentTitle);

        textViewTitle.setText(assessmentTitle);

        FloatingActionButton buttonEditAssessment = findViewById(R.id.btn_edit_assessment);
        buttonEditAssessment.setOnClickListener(v -> {
//            Intent editAssessmentIntent = new Intent(AssessmentActivity.this, AddEDitAssessmentActivity.class);
//            editAssessmentIntent.putExtra(AddEditAssessmentActivity.EXTRA_);
//            editAssessmentIntent.putExtra(AddEditAssessmentActivity.EXTRA_);
//            editAssessmentIntent.putExtra(AddEditAssessmentActivity.EXTRA_);
//            editAssessmentIntent.putExtra(AddEditAssessmentActivity.EXTRA_);
//            startActivityForResult(editAssessmentIntent, AddEditAssessmentActivity.EDIT_NOTE_REQUEST);
        });
    }
}
