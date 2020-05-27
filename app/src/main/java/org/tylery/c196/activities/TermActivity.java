package org.tylery.c196.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.tylery.c196.R;
import org.tylery.c196.entities.TermEntity;
import org.tylery.c196.viewmodel.TermViewModel;

public class TermActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "org.tylery.c196.activities.ID";
    public static final String EXTRA_TITLE =
            "org.tylery.c196.activities.EXTRA_TITLE";
    public static final String EXTRA_START_DATE =
            "org.tylery.c196.activities.START_DATE";
    public static final String EXTRA_END_DATE =
            "org.tylery.c196.activities.END_DATE";


    public static final int EDIT_TERM_REQUEST = 2;

    private TermViewModel termViewModel;

    private int termID;
    private TextView textViewTitle;
    private TextView textViewStartDate;
    private TextView textViewEndDate;
    private Button courseListButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        termViewModel = new ViewModelProvider(this).get(TermViewModel.class);
        setContentView(R.layout.activity_term);

        textViewTitle = findViewById(R.id.textview_detailed_term_title);
        textViewStartDate = findViewById(R.id.textview_detailed_term_startdate);
        textViewEndDate = findViewById(R.id.textview_detailed_term_enddate);
        courseListButton = findViewById(R.id.edit_term_save_button);

        Intent intent = getIntent();
        termID = intent.getIntExtra(EXTRA_ID, -1);
        textViewTitle.setText(intent.getStringExtra(EXTRA_TITLE));
        textViewStartDate.setText(intent.getStringExtra(EXTRA_START_DATE));
        textViewEndDate.setText(intent.getStringExtra(EXTRA_END_DATE));

        courseListButton.setOnClickListener(v -> {
            Intent loadCourseListIntent = new Intent();
            loadCourseListIntent.putExtra(EXTRA_ID, termID);
            startActivity(loadCourseListIntent);
        });

        FloatingActionButton buttonEditTerm = findViewById(R.id.btn_add_term);
        buttonEditTerm.setOnClickListener(v -> {
            Intent editTermIntent = new Intent(TermActivity.this, AddEditTermActivity.class);
            editTermIntent.putExtra(AddEditTermActivity.EXTRA_ID, intent.getIntExtra(EXTRA_ID, -1));
            editTermIntent.putExtra(AddEditTermActivity.EXTRA_TITLE, intent.getStringExtra(EXTRA_TITLE));
            editTermIntent.putExtra(AddEditTermActivity.EXTRA_START_DATE, intent.getStringExtra(EXTRA_START_DATE));
            editTermIntent.putExtra(AddEditTermActivity.EXTRA_END_DATE, intent.getStringExtra(EXTRA_END_DATE));
            startActivityForResult(editTermIntent, EDIT_TERM_REQUEST);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_TERM_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddEditTermActivity.EXTRA_TITLE);
            String startDate = data.getStringExtra(AddEditTermActivity.EXTRA_START_DATE);
            String endDate = data.getStringExtra(AddEditTermActivity.EXTRA_END_DATE);
            int id = data.getIntExtra(AddEditTermActivity.EXTRA_ID, -1);
            if(id == -1) {
                Toast.makeText(this, "Error, term not saved", Toast.LENGTH_SHORT).show();
                return;
            }

            TermEntity termEntity = new TermEntity(title, startDate, endDate);
            termEntity.setId(id);
            termViewModel.update(termEntity);

            Toast.makeText(this, "Term updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
