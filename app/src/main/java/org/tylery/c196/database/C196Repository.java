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
import org.tylery.c196.generics.GenericDao;
import org.tylery.c196.generics.GenericEntity;

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

    public void insert(GenericDao dao, GenericEntity entity) {
        new InsertAsyncTask(dao).execute(entity);
    }

    public void update(GenericDao dao, GenericEntity entity) {
        new UpdateAsyncTask(dao).execute(entity);
    }

    public void delete(GenericDao dao, GenericEntity entity) {
        new DeleteAsyncTask(dao).execute(entity);
    }

    public LiveData<List<TermEntity>> getAllTerms() {
        return allTerms;
    }

    public LiveData<List<CourseEntity>> getTermCourses(int termID) {

    }
    public LiveData<List<NoteEntity>> getCourseNotes(int courseID) {

    }
    public LiveData<List<AssessmentEntity>> getCourseAssessments(int courseID) {

    }

    private static class InsertAsyncTask extends AsyncTask<GenericEntity, Void, Void> {
        private GenericDao dao;

        public InsertAsyncTask(GenericDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(GenericEntity... genericEntities) {
            dao.insert(genericEntities[0]);
            return null;
        }
    }
    private static class UpdateAsyncTask extends AsyncTask<GenericEntity, Void, Void> {
        private GenericDao dao;

        public UpdateAsyncTask(GenericDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(GenericEntity... genericEntities) {
            dao.update(genericEntities[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<GenericEntity, Void, Void> {
        private GenericDao dao;

        public DeleteAsyncTask(GenericDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(GenericEntity... genericEntities) {
            dao.delete(genericEntities[0]);
            return null;
        }
    }

}
