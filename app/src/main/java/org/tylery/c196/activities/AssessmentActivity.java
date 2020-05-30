package org.tylery.c196.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.tylery.c196.R;
import org.tylery.c196.entities.AssessmentEntity;
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
    public static final String EXTRA_ASSESSMENT_TYPE =
            "org.tylery.c196.activities.ASSESSMENT_TYPE";
    public static final int TYPE_PA = 0;
    public static final int TYPE_OA = 1;

    private AssessmentViewModel assessmentViewModel;

    private int courseID;
    private String courseTitle;
    private int assessmentID;
    private TextView textViewTitle;
    private TextView textViewDueDate;
    private TextView textViewType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assessmentViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);
        setContentView(R.layout.activity_assessment);

        textViewTitle = findViewById(R.id.detailed_assessment_due_date);
        textViewDueDate = findViewById(R.id.detailed_assessment_due_date);
        textViewType = findViewById(R.id.detailed_assessment_type);

        Intent parentIntent = getIntent();
        courseID = parentIntent.getIntExtra(EXTRA_ASSESSMENT_COURSE_ID, -1);
        assessmentID = parentIntent.getIntExtra(EXTRA_ASSESSMENT_ID, -1);
        courseTitle = parentIntent.getStringExtra(EXTRA_ASSESSMENT_COURSE_TITLE);
        String assessmentTitle = parentIntent.getStringExtra(EXTRA_ASSESSMENT_TITLE);

        setTitle(courseTitle + " | " + assessmentTitle);

        textViewTitle.setText(assessmentTitle);
        textViewDueDate.setText(parentIntent.getStringExtra(EXTRA_ASSESSMENT_DUE_DATE));
        int type = parentIntent.getIntExtra(EXTRA_ASSESSMENT_TYPE, -1);
        textViewType.setText(getAssessmentType(type));

        FloatingActionButton buttonEditAssessment = findViewById(R.id.btn_edit_assessment);
        buttonEditAssessment.setOnClickListener(v -> {
            Intent editAssessmentIntent = new Intent(AssessmentActivity.this, AddEditAssessmentActivity.class);
            editAssessmentIntent.putExtra(AddEditAssessmentActivity.EXTRA_ASSESSMENT_ID, assessmentID);
            editAssessmentIntent.putExtra(AddEditAssessmentActivity.EXTRA_COURSE_ID, courseID);
            editAssessmentIntent.putExtra(AddEditAssessmentActivity.EXTRA_COURSE_ASSESSMENT_TITLE, assessmentTitle);
            editAssessmentIntent.putExtra(AddEditAssessmentActivity.EXTRA_COURSE_ASSESSMENT_GOAL_DATE, parentIntent.getStringExtra(EXTRA_ASSESSMENT_DUE_DATE));
            editAssessmentIntent.putExtra(AddEditAssessmentActivity.EXTRA_COURSE_ASSESSMENT_ALERT, parentIntent.getBooleanExtra(EXTRA_ASSESSMENT_ALARM, false));
            editAssessmentIntent.putExtra(AddEditAssessmentActivity.EXTRA_ASSESSMENT_TYPE, parentIntent.getStringExtra(EXTRA_ASSESSMENT_TYPE));
            startActivityForResult(editAssessmentIntent, AddEditAssessmentActivity.EDIT_ASSESSMENT_REQUEST);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == AddEditAssessmentActivity.EDIT_ASSESSMENT_REQUEST && resultCode == RESULT_OK) {
            int assessmentID = data.getIntExtra(AddEditAssessmentActivity.EXTRA_ASSESSMENT_ID, -1);
            String assessmentName = data.getStringExtra(AddEditAssessmentActivity.EXTRA_COURSE_ASSESSMENT_TITLE);
            int assessmentType = data.getIntExtra(AddEditAssessmentActivity.EXTRA_ASSESSMENT_TYPE, -1);
            String assessmentGoalDate = data.getStringExtra(AddEditAssessmentActivity.EXTRA_COURSE_ASSESSMENT_GOAL_DATE);
            boolean assessmentAlertEnabled = data.getBooleanExtra(AddEditAssessmentActivity.EXTRA_COURSE_ASSESSMENT_ALERT, false);

            if( assessmentID == -1
                    || assessmentType == -1) {
                Toast.makeText(this, "Error, assessment not saved", Toast.LENGTH_SHORT).show();
                return;
            }

            AssessmentEntity assessmentEntity = new AssessmentEntity(courseID,
                    assessmentName, assessmentType, assessmentGoalDate, assessmentAlertEnabled);

            assessmentEntity.setId(assessmentID);
            assessmentViewModel.update(assessmentEntity);

            Toast.makeText(this, "Assessment updated", Toast.LENGTH_SHORT).show();
        }  else {
            Toast.makeText(this, "Assessment not updated", Toast.LENGTH_SHORT).show();
        }
    }

    public static String getAssessmentType(int type) {
        String result;
        switch (type) {
            case TYPE_PA:
                result = "PA";
                break;
            case TYPE_OA:
                result = "OA";
                break;
            default:
                result = "";
                break;
        }
        return result;
    }
}
