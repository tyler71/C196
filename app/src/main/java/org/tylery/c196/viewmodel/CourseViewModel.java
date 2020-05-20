package org.tylery.c196.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.tylery.c196.database.C196Repository;
import org.tylery.c196.entities.CourseEntity;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {
    private C196Repository repo;

    public CourseViewModel(@NonNull Application application) {
        super(application);
        repo = new C196Repository(application);
    }

    public void insert(CourseEntity courseEntity) {
        repo.insert(courseEntity);
    }
    public void update(CourseEntity courseEntity) {
        repo.update(courseEntity);
    }
    public void delete(CourseEntity courseEntity) {
        repo.delete(courseEntity);
    }

    public LiveData<List<CourseEntity>> getTermCourses(int termID) {
        return repo.getTermCourses(termID);
    }
}
