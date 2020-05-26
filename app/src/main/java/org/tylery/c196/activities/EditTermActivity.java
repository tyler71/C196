package org.tylery.c196.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import org.tylery.c196.R;

public class EditTermActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE =
            "org.tylery.c196.activities.EXTRA_TITLE";
    public static final String EXTRA_START_DATE =
            "org.tylery.c196.activities.START_DATE";
    public static final String EXTRA_END_DATE =
            "org.tylery.c196.activities.END_DATE";

    private EditText editTextTitle;
    private EditText editTextStartDate;
    private EditText editTextEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term);

        editTextTitle = findViewById(R.id.edit_term_title);
        editTextStartDate = findViewById(R.id.edit_term_start_date);
        editTextEndDate = findViewById(R.id.edit_term_end_date);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
        setTitle("Editing Note");
    }

    private void saveTerm() {
        String termTitle = editTextTitle.getText().toString();
        String startDate = editTextStartDate.getText().toString();
        String endDate = editTextEndDate.getText().toString();

        if (termTitle.trim().isEmpty()
                || startDate.trim().isEmpty()
                || endDate.trim().isEmpty()) {
            Toast.makeText(this, "Empty fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, termTitle);
        data.putExtra(EXTRA_START_DATE, startDate);
        data.putExtra(EXTRA_END_DATE, endDate);

        setResult(RESULT_OK, data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_term, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save_term:
                saveTerm();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
