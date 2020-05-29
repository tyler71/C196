package org.tylery.c196.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

    public static final int REQUEST_ADD_COURSE = 1;
    public static final int REQUEST_EDIT_COURSE = 2;

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
            if(intent.getBooleanExtra(EXTRA_COURSE_ALERT, false))
                editCheckboxCourseAlarm.performClick();
            editRadioStatus.check(getBtnID(intent.getIntExtra(EXTRA_COURSE_STATUS, -1)));
            editTextCourseMentorName.setText(intent.getStringExtra(EXTRA_COURSE_MENTOR_NAME));
            getEditTextCourseMentorPhone.setText(intent.getStringExtra(EXTRA_COURSE_MENTOR_PHONE));
            editTextCourseMentorEmail.setText(intent.getStringExtra(EXTRA_COURSE_MENTOR_EMAIL));
        } else {
            setTitle("Add Course");
        }
    }

    private void saveCourse() {
        String courseTitle = editTextTitle.getText().toString();
        String courseStartDate = editTextStartDate.getText().toString();
        String courseEndDate = editTextEndDate.getText().toString();
        boolean courseAlarmEnabled = editCheckboxCourseAlarm.isChecked();
        int courseStatus = getRadioStatus(editRadioStatus.getCheckedRadioButtonId());
        String courseMentorName = editTextCourseMentorName.getText().toString();
        String courseMentorPhone = getEditTextCourseMentorPhone.getText().toString();
        String courseMentorEmail = editTextCourseMentorEmail.getText().toString();

        if(courseTitle.trim().isEmpty()
            || courseStartDate.trim().isEmpty()
            || courseEndDate.trim().isEmpty()
            || courseStatus == -1
            || courseMentorName.trim().isEmpty()
            || courseMentorPhone.trim().isEmpty()
            || courseMentorEmail.trim().isEmpty()) {
            Toast.makeText(this, "Course not saved. Empty fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_COURSE_TITLE, courseTitle);
        data.putExtra(EXTRA_COURSE_START_DATE, courseStartDate);
        data.putExtra(EXTRA_COURSE_END_DATE, courseEndDate);
        data.putExtra(EXTRA_COURSE_ALERT, courseAlarmEnabled);
        data.putExtra(EXTRA_COURSE_STATUS, courseStatus);
        data.putExtra(EXTRA_COURSE_MENTOR_NAME, courseMentorName);
        data.putExtra(EXTRA_COURSE_MENTOR_PHONE, courseMentorPhone);
        data.putExtra(EXTRA_COURSE_MENTOR_EMAIL, courseMentorEmail);

        int courseID = getIntent().getIntExtra(EXTRA_COURSE_ID, -1);
        if(courseID != -1) {
            data.putExtra(EXTRA_COURSE_ID, courseID);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       switch (item.getItemId()) {
           case R.id.menu_add_edit_save:
               saveCourse();
               return true;
           default:
               return super.onOptionsItemSelected(item);
       }
    }

    @Override
    public boolean onSupportNavigateUp() {
       finish();
       return true;
    }

    private int getBtnID(int id) {
       int btn_id;
       switch (id) {
           case CourseActivity.STATUS_DROPPED:
               btn_id = R.id.radio_course_status_dropped;
               break;
           case CourseActivity.STATUS_PLANNED:
               btn_id = R.id.radio_course_status_planned;
               break;
           case CourseActivity.STATUS_IN_PROGRESS:
               btn_id = R.id.radio_course_status_in_progress;
               break;
           case CourseActivity.STATUS_COMPLETED:
               btn_id = R.id.radio_course_status_completed;
               break;
           default:
               btn_id = -1;
       }
       return btn_id;
    }
    private int getRadioStatus(int btnID) {
        int statusID;
        switch (btnID) {
            case R.id.radio_course_status_dropped:
                statusID = CourseActivity.STATUS_DROPPED;
                break;
            case R.id.radio_course_status_planned:
                statusID = CourseActivity.STATUS_PLANNED;
                break;
            case R.id.radio_course_status_in_progress:
                statusID = CourseActivity.STATUS_IN_PROGRESS;
                break;
            case R.id.radio_course_status_completed:
                statusID = CourseActivity.STATUS_COMPLETED;
                break;
            default:
                statusID = -1;
        }
        return statusID;
    }
}
