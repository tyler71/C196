package org.tylery.c196.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

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

    private EditText editTextTitle;
    private EditText editTextStartDate;
    private EditText editTextEndDate;
    private CheckBox editCheckboxCourseAlarm;
    private RadioGroup editRadioStatus;
    private EditText editTextCourseMentorName;
    private EditText getEditTextCourseMentorPhone;
    private EditText editTextCourseMentorEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_course);

        editTextTitle = findViewById(R.id.edit_course_title);
        editTextStartDate = findViewById(R.id.edit_course_start_date);
        editTextEndDate = findViewById(R.id.edit_course_end_date);
        editCheckboxCourseAlarm = findViewById(R.id.edit_course_alarm);
        editRadioStatus = findViewById(R.id.edit_course_radio_status);
        editTextCourseMentorName = findViewById(R.id.edit_course_mentor_name);
        getEditTextCourseMentorPhone = findViewById(R.id.edit_course_mentor_phone_number);
        editTextCourseMentorEmail = findViewById(R.id.edit_course_mentor_email_address);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_COURSE_ID)) {
            setTitle("Edit Course");
            editTextTitle.setText(intent.getStringExtra(EXTRA_COURSE_TITLE));
            editTextStartDate.setText(intent.getStringExtra(EXTRA_COURSE_START_DATE));
            editTextEndDate.setText(intent.getStringExtra(EXTRA_COURSE_END_DATE));
//            editCheckboxCourseAlarm.setActivated(intent.getBooleanExtra(EXTRA_COURSE_ALERT, false));
            if(intent.getBooleanExtra(EXTRA_COURSE_ALERT, false))
                editCheckboxCourseAlarm.performClick();
//            Should be passed ID of the correct radio button
            editRadioStatus.check(intent.getIntExtra(EXTRA_COURSE_STATUS, -1));
            editTextCourseMentorName.setText(intent.getStringExtra(EXTRA_COURSE_MENTOR_NAME));
            getEditTextCourseMentorPhone.setText(intent.getStringExtra(EXTRA_COURSE_MENTOR_PHONE));
            editTextCourseMentorEmail.setText(intent.getStringExtra(EXTRA_COURSE_MENTOR_EMAIL));
        } else {
            setTitle("Add Course");
        }
    }
}
