package org.tylery.c196.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import org.tylery.c196.dao.AssessmentDao;
import org.tylery.c196.dao.CourseDao;
import org.tylery.c196.dao.NoteDao;
import org.tylery.c196.dao.TermDao;
import org.tylery.c196.entities.AssessmentEntity;
import org.tylery.c196.entities.CourseEntity;
import org.tylery.c196.entities.NoteEntity;
import org.tylery.c196.entities.TermEntity;

import java.util.List;

public class C196Repository {
    private TermDao termDao;
    private LiveData<List<TermEntity>> allTerms;

    private CourseDao courseDao;
    private NoteDao noteDao;
    private AssessmentDao assessmentDao;

    public C196Repository(Application application) {
        C196Database database = C196Database.getInstance(application);
        termDao = database.termDao();
        allTerms = termDao.getAllTerms();

        courseDao = database.courseDao();
        assessmentDao = database.assessmentDao();
        noteDao = database.noteDao();
    }

    public void insert(TermEntity term) {

    }

    public void update(TermEntity term) {

    }

    public void delete(TermEntity term) {

    }

    public LiveData<List<TermEntity>> getAllTerms() {
        return allTerms;
    }


    public void insert(CourseEntity course) {

    }

    public void update(CourseEntity course) {

    }

    public void delete(CourseEntity course) {

    }

    public LiveData<List<CourseEntity>> getTermCourses(int termID) {

    }


    public void insert(NoteEntity note) {

    }

    public void update(NoteEntity note) {

    }

    public void delete(NoteEntity note) {

    }

    public LiveData<List<NoteEntity>> getCourseNotes(int courseID) {

    }

    public void insert(AssessmentEntity assessment) {

    }

    public void update(AssessmentEntity assessment) {

    }

    public void delete(AssessmentEntity assessment) {

    }

    public LiveData<List<AssessmentEntity>> getCourseAssessments(int courseID) {

    }

    private static class InsertAsyncTask extends AsyncTask<TermEntity, Void, Void> {
        private TermDao termDao;

        public InsertAsyncTask(TermDao termDao) {
            this.termDao = termDao;
        }

        @Override
        protected Void doInBackground(TermEntity... termEntities) {
            termDao.insert(termEntities[0]);
            return null;
        }
    }

}
