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
    private LiveData<List<TermEntity>> mAllTerms;

    private CourseDao courseDao;
    private LiveData<List<CourseEntity>> mTermCourses;

    private NoteDao noteDao;
    private LiveData<List<NoteEntity>> mCourseNotes;

    private AssessmentDao assessmentDao;
    private LiveData<List<AssessmentEntity>> mCourseAssessments;

    private int termID;
    private int courseID;

    public C196Repository(Application application) {
        C196Database database = C196Database.getInstance(application);
        termDao = database.termDao();
        mAllTerms = termDao.getAllTerms();

        courseDao = database.courseDao();
        mTermCourses = courseDao.getTermCourses(termID);

        assessmentDao = database.assessmentDao();
        mCourseAssessments = assessmentDao.getCourseAssessments(courseID);

        noteDao = database.noteDao();
        mCourseNotes = noteDao.getCourseNotes(courseID);
    }

    public void insert(TermEntity termEntity) {
        new InsertAsyncTask(termDao).execute(termEntity);
    }
    public void insert(CourseEntity courseEntity) {
        new InsertAsyncTask(courseDao).execute(courseEntity);
    }
    public void insert(NoteEntity noteEntity) {
        new InsertAsyncTask(noteDao).execute(noteEntity);
    }
    public void insert(AssessmentEntity assessmentEntity) {
        new InsertAsyncTask(assessmentDao).execute(assessmentEntity);
    }

    public void update(TermEntity termEntity) {
        new UpdateAsyncTask(termDao).execute(termEntity);
    }
    public void update(CourseEntity courseEntity) {
        new UpdateAsyncTask(courseDao).execute(courseEntity);
    }
    public void update(NoteEntity noteEntity) {
        new UpdateAsyncTask(noteDao).execute(noteEntity);
    }
    public void update(AssessmentEntity assessmentEntity) {
        new UpdateAsyncTask(assessmentDao).execute(assessmentEntity);
    }

    public void delete(TermEntity termEntity) {
        new DeleteAsyncTask(termDao).execute(termEntity);
    }
    public void delete(CourseEntity courseEntity) {
        new DeleteAsyncTask(courseDao).execute(courseEntity);
    }
    public void delete(NoteEntity noteEntity) {
        new DeleteAsyncTask(noteDao).execute(noteEntity);
    }
    public void delete(AssessmentEntity assessmentEntity) {
        new DeleteAsyncTask(assessmentDao).execute(assessmentEntity);
    }


    public LiveData<List<TermEntity>> getAllTerms() {
        return mAllTerms;
    }

    public LiveData<List<CourseEntity>> getTermCourses(int termID) {
        return mTermCourses;
    }
    public LiveData<List<NoteEntity>> getCourseNotes(int courseID) {
        return mCourseNotes;
    }
    public LiveData<List<AssessmentEntity>> getCourseAssessments(int courseID) {
        return mCourseAssessments;
    }

    private static class InsertAsyncTask extends AsyncTask<GenericEntity, Void, Void> {
        private GenericDao dao;

        private InsertAsyncTask(GenericDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(GenericEntity... genericEntities) {
            GenericEntity entity = genericEntities[0];
            dao.insert(entity);
            return null;
        }
    }
    private static class UpdateAsyncTask extends AsyncTask<GenericEntity, Void, Void> {
        private GenericDao dao;

        private UpdateAsyncTask(GenericDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(GenericEntity... genericEntities) {
            dao.update((AssessmentEntity) genericEntities[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<GenericEntity, Void, Void> {
        private GenericDao dao;

        private DeleteAsyncTask(GenericDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(GenericEntity... genericEntities) {
            dao.delete((AssessmentEntity) genericEntities[0]);
            return null;
        }
    }


}
