package org.tylery.c196.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.tylery.c196.R;

public class CourseActivity extends AppCompatActivity {
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

    public static final int EDIT_COURSE_REQUEST = 5;

    private int courseID;
    private TextView textViewTitle;
    private TextView textViewStartDate;
    private TextView textViewEndDate;
    private TextView status;
    private TextView textViewCourseMentorName;
    private TextView textViewCourseMentorPhone;
    private TextView textViewCourseMentorEmail;
    private boolean alarmEnabled;
    private ImageView imageViewAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        textViewTitle = findViewById(R.id.detailed_course_title);
        textViewStartDate = findViewById(R.id.detailed_course_start_date);
        textViewEndDate = findViewById(R.id.detailed_course_end_date);
        status = findViewById(R.id.detailed_course_status);
        imageViewAlarm = findViewById(R.id.detailed_image_alarm);
        textViewCourseMentorName = findViewById(R.id.detailed_course_mentor_name);
        textViewCourseMentorPhone = findViewById(R.id.detailed_course_mentor_phone_number);
        textViewCourseMentorEmail = findViewById(R.id.detailed_course_mentor_email_address);

        Intent parentIntent = getIntent();
        courseID = parentIntent.getIntExtra(EXTRA_COURSE_ID, -1);
        textViewTitle.setText(parentIntent.getStringExtra(EXTRA_COURSE_TITLE));
        textViewStartDate.setText(parentIntent.getStringExtra(EXTRA_COURSE_START_DATE));
        textViewEndDate.setText(parentIntent.getStringExtra(EXTRA_COURSE_END_DATE));
        status.setText(parentIntent.getStringExtra(EXTRA_COURSE_STATUS));
        textViewCourseMentorName.setText(parentIntent.getStringExtra(EXTRA_COURSE_MENTOR_NAME));
        textViewCourseMentorPhone.setText(parentIntent.getStringExtra(EXTRA_COURSE_MENTOR_PHONE));
        textViewCourseMentorEmail.setText(parentIntent.getStringExtra(EXTRA_COURSE_MENTOR_EMAIL));
        alarmEnabled = parentIntent.getBooleanExtra(EXTRA_COURSE_ALERT, false);
        if(alarmEnabled) {
            imageViewAlarm.setVisibility(View.VISIBLE);
        } else {
            imageViewAlarm.setVisibility(View.INVISIBLE);
        }

        FloatingActionButton buttonEditCourse = findViewById(R.id.btn_edit_course);
        buttonEditCourse.setOnClickListener(v -> {
            Intent editCourseIntent = new Intent(CourseActivity.this, AddEditCourseActivity.class);
            editCourseIntent.putExtra(AddEditCourseActivity.EXTRA_COURSE_ID, courseID);
            editCourseIntent.putExtra(AddEditCourseActivity.EXTRA_COURSE_TITLE, parentIntent.getStringExtra(EXTRA_COURSE_TITLE));
            editCourseIntent.putExtra(AddEditCourseActivity.EXTRA_COURSE_START_DATE, parentIntent.getStringExtra(EXTRA_COURSE_START_DATE));
            editCourseIntent.putExtra(AddEditCourseActivity.EXTRA_COURSE_END_DATE, parentIntent.getStringExtra(EXTRA_COURSE_END_DATE));
            editCourseIntent.putExtra(AddEditCourseActivity.EXTRA_COURSE_STATUS, parentIntent.getStringExtra(EXTRA_COURSE_STATUS));
            editCourseIntent.putExtra(AddEditCourseActivity.EXTRA_COURSE_MENTOR_NAME, parentIntent.getStringExtra(EXTRA_COURSE_MENTOR_NAME));
            editCourseIntent.putExtra(AddEditCourseActivity.EXTRA_COURSE_MENTOR_PHONE, parentIntent.getStringExtra(EXTRA_COURSE_MENTOR_PHONE));
            editCourseIntent.putExtra(AddEditCourseActivity.EXTRA_COURSE_MENTOR_EMAIL, parentIntent.getStringExtra(EXTRA_COURSE_MENTOR_EMAIL));
            editCourseIntent.putExtra(AddEditCourseActivity.EXTRA_COURSE_ALERT, alarmEnabled);
            startActivityForResult(editCourseIntent, EDIT_COURSE_REQUEST);
        });
    }
}
