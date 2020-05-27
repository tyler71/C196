package org.tylery.c196.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.tylery.c196.R;
import org.tylery.c196.adapters.CourseAdapter;
import org.tylery.c196.viewmodel.CourseViewModel;

public class CourseListActivity extends AppCompatActivity {
    private int termID;

    private CourseViewModel courseViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);

        Intent intent = getIntent();
        termID = intent.getIntExtra(TermActivity.EXTRA_ID, -1);

        RecyclerView recyclerView = findViewById(R.id.courseListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final CourseAdapter adapter = new CourseAdapter();
        recyclerView.setAdapter(adapter);

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getTermCourses(termID).observe(this, courseEntities -> adapter.setCourses(courseEntities));
    }
}
