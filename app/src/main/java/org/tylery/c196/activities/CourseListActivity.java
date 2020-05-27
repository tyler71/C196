package org.tylery.c196.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.tylery.c196.R;
import org.tylery.c196.adapters.CourseAdapter;
import org.tylery.c196.entities.CourseEntity;
import org.tylery.c196.viewmodel.CourseViewModel;

public class CourseListActivity extends AppCompatActivity {
    private int termID;

    private CourseViewModel courseViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);

        Intent loadCourseListIntent = getIntent();
        termID = loadCourseListIntent.getIntExtra(TermActivity.EXTRA_ID, -1);

        RecyclerView recyclerView = findViewById(R.id.courseListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final CourseAdapter adapter = new CourseAdapter();
        recyclerView.setAdapter(adapter);

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getTermCourses(termID).observe(this, courseEntities -> adapter.setCourses(courseEntities));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                CourseEntity deletedCourse = adapter.getCourseAt(viewHolder.getAdapterPosition());
                courseViewModel.delete(deletedCourse);
                Toast.makeText(CourseListActivity.this, "Course deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(courseEntity -> {
            Intent intent = new Intent(CourseListActivity.this, CourseActivity.class);
            intent.putExtra(AddEditCourseActivity.EXTRA_COURSE_ID, courseEntity.getId());
            intent.putExtra(AddEditCourseActivity.EXTRA_COURSE_TITLE, courseEntity.getTitle());
            intent.putExtra(AddEditCourseActivity.EXTRA_COURSE_START_DATE, courseEntity.getStartDate());
            intent.putExtra(AddEditCourseActivity.EXTRA_COURSE_END_DATE, courseEntity.getEndDate());
            intent.putExtra(AddEditCourseActivity.EXTRA_COURSE_ALERT, courseEntity.isAlert());
            intent.putExtra(AddEditCourseActivity.EXTRA_COURSE_STATUS, courseEntity.getStatus());
            intent.putExtra(AddEditCourseActivity.EXTRA_COURSE_MENTOR_NAME, courseEntity.getMentorName());
            intent.putExtra(AddEditCourseActivity.EXTRA_COURSE_MENTOR_EMAIL, courseEntity.getMentorEmail());
            intent.putExtra(AddEditCourseActivity.EXTRA_COURSE_MENTOR_PHONE, courseEntity.getMentorPhone());
            startActivity(intent);
        });
    }
}
