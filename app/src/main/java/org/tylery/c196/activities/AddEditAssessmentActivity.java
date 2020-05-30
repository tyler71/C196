package org.tylery.c196.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.tylery.c196.R;

public class AddEditAssessmentActivity extends AppCompatActivity {
    public static final String EXTRA_ASSESSMENT_ID =
            "org.tylery.c196.activities.COURSE_ASSESSMENT_ID";
    public static final String EXTRA_COURSE_ID =
            "org.tylery.c196.activities.COURSE_COURSE_ID";
    public static final String EXTRA_COURSE_ASSESSMENT_TITLE =
            "org.tylery.c196.activities.COURSE_ASSESSMENT_TITLE";
    public static final String EXTRA_COURSE_ASSESSMENT_GOAL_DATE =
            "org.tylery.c196.activities.COURSE_ASSESSMENT_GOAL_DATE";
    public static final String EXTRA_COURSE_ASSESSMENT_ALERT =
            "org.tylery.c196.activities.COURSE_ASSESSMENT_ALERT";

    private int courseID;
    private EditText editTextTitle;
    private EditText editTextGoalDate;
    private CheckBox editCheckboxAlarmEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_assessment);

        editTextTitle = findViewById(R.id.edit_assessment_name);
        editTextGoalDate = findViewById(R.id.edit_assessment_goal_date);
        editCheckboxAlarmEnabled = findViewById(R.id.edit_assessment_alarm);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

        Intent parentIntent = getIntent();
        parentIntent.getIntExtra()
        if(parentIntent.hasExtra(EXTRA_ASSESSMENT_ID)) {
            setTitle("Edit Assessment");
            editTextTitle.setText(parentIntent.getStringExtra(EXTRA_COURSE_ASSESSMENT_TITLE));
            editTextGoalDate.setText(parentIntent.getStringExtra(EXTRA_COURSE_ASSESSMENT_GOAL_DATE));
            if(parentIntent.getBooleanExtra(EXTRA_COURSE_ASSESSMENT_ALERT, false))
                editCheckboxAlarmEnabled.performClick();
        } else {
            setTitle("Add Assessment");
        }

    }

    private void saveAssessment() {
        String assessmentTitle = editTextTitle.getText().toString();
        String assessmentGoalDate = editTextGoalDate.getText().toString();
        boolean alarmEnabled = editCheckboxAlarmEnabled.isEnabled();

        if(assessmentTitle.trim().isEmpty()
            || assessmentGoalDate.trim().isEmpty()) {
            Toast.makeText(this, "Assessment not saved. Empty fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_COURSE_ASSESSMENT_TITLE, assessmentTitle);
        data.putExtra(EXTRA_COURSE_ASSESSMENT_GOAL_DATE, assessmentGoalDate);
        data.putExtra(EXTRA_COURSE_ASSESSMENT_ALERT, alarmEnabled);
        int course
    }
}
