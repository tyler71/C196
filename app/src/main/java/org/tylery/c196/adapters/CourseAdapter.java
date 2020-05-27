package org.tylery.c196.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.tylery.c196.R;
import org.tylery.c196.entities.CourseEntity;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseHolder> {
    List<CourseEntity> courses = new ArrayList<>();

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_item, parent, false);
        return new CourseHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder holder, int position) {
        CourseEntity currentCourse = courses.get(position);
        holder.textViewCourseTitle.setText(currentCourse.getTitle());
        holder.textViewCourseEndDate.setText(currentCourse.getEndDate());
        holder.textViewStatus.setText(currentCourse.getStatus());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void setCourses(List<CourseEntity> courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }

    class CourseHolder extends RecyclerView.ViewHolder {
        private TextView textViewCourseTitle;
//        private TextView textViewCourseStartDate;
        private TextView textViewCourseEndDate;
//        private boolean  alert;
        private TextView textViewStatus;
//        private TextView textViewMentorName;
//        private TextView textViewMentorPhone;
//        private TextView textViewmentorEmail;

        public CourseHolder(@NonNull View itemView) {
            super(itemView);
            textViewCourseTitle = itemView.findViewById(R.id.text_view_course_title);
            textViewCourseEndDate = itemView.findViewById(R.id.text_view_course_end_date);
            textViewStatus = itemView.findViewById(R.id.text_view_course_status);
        }
    }
}
