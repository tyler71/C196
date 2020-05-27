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
    private LiveData<List<TermEntity>> mAllTerms;

    private CourseDao courseDao;
    private LiveData<List<CourseEntity>> mTermCourses;

    private NoteDao noteDao;
    private LiveData<List<NoteEntity>> mCourseNotes;

    private AssessmentDao assessmentDao;
    private LiveData<List<AssessmentEntity>> mCourseAssessments;


    public C196Repository(Application application) {
        C196Database database = C196Database.getInstance(application);
        termDao = database.termDao();
        mAllTerms = termDao.getAllTerms();

        courseDao = database.courseDao();
        assessmentDao = database.assessmentDao();
        noteDao = database.noteDao();
    }

    public void insert(TermEntity termEntity) {
        new InsertAsyncTerm(termDao).execute(termEntity);
    }
    public void insert(CourseEntity courseEntity) {
        new InsertAsyncCourse(courseDao).execute(courseEntity);
    }
    public void insert(NoteEntity noteEntity) {
        new InsertAsyncNote(noteDao).execute(noteEntity);
    }
    public void insert(AssessmentEntity assessmentEntity) {
        new InsertAsyncAssessment(assessmentDao).execute(assessmentEntity);
    }

    public void update(TermEntity termEntity) {
        new UpdateAsyncTerm(termDao).execute(termEntity);
    }
    public void update(CourseEntity courseEntity) {
        new UpdateAsyncCourse(courseDao).execute(courseEntity);
    }
    public void update(NoteEntity noteEntity) {
        new UpdateAsyncNote(noteDao).execute(noteEntity);
    }
    public void update(AssessmentEntity assessmentEntity) {
        new UpdateAsyncAssessment(assessmentDao).execute(assessmentEntity);
    }

    public void delete(TermEntity termEntity) {
        new DeleteAsyncTerm(termDao).execute(termEntity);
    }
    public void delete(CourseEntity courseEntity) {
        new DeleteAsyncCourse(courseDao).execute(courseEntity);
    }
    public void delete(NoteEntity noteEntity) {
        new DeleteAsyncNote(noteDao).execute(noteEntity);
    }
    public void delete(AssessmentEntity assessmentEntity) {
        new DeleteAsyncAssessment(assessmentDao).execute(assessmentEntity);
    }


    public LiveData<List<TermEntity>> getAllTerms() {
        return mAllTerms;
    }

    public LiveData<List<CourseEntity>> getTermCourses(int termID) {
        return courseDao.getTermCourses(termID);
    }
    public LiveData<List<AssessmentEntity>> getCourseAssessments(int courseID) {
        return assessmentDao.getCourseAssessments(courseID);
    }
    public LiveData<List<NoteEntity>> getCourseNotes(int courseID) {
        return noteDao.getCourseNotes(courseID);
    }

    private static class InsertAsyncTerm extends AsyncTask<TermEntity, Void, Void> {
        private TermDao dao;

        private InsertAsyncTerm(TermDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(TermEntity... termEntities) {
            dao.insert(termEntities[0]);
            return null;
        }
    }
    private static class InsertAsyncCourse extends AsyncTask<CourseEntity, Void, Void> {
        private CourseDao dao;

        private InsertAsyncCourse(CourseDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CourseEntity... courseEntities) {
            dao.insert(courseEntities[0]);
            return null;
        }
    }
    private static class InsertAsyncAssessment extends AsyncTask<AssessmentEntity, Void, Void> {
        private AssessmentDao dao;

        private InsertAsyncAssessment(AssessmentDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(AssessmentEntity... assessmentEntities) {
            dao.insert(assessmentEntities[0]);
            return null;
        }
    }
    private static class InsertAsyncNote extends AsyncTask<NoteEntity, Void, Void> {
        private NoteDao dao;

        private InsertAsyncNote(NoteDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            dao.insert(noteEntities[0]);
            return null;
        }
    }

    private static class UpdateAsyncTerm extends AsyncTask<TermEntity, Void, Void> {
        private TermDao dao;

        private UpdateAsyncTerm(TermDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(TermEntity... termEntities) {
            dao.update(termEntities[0]);
            return null;
        }
    }
    private static class UpdateAsyncCourse extends AsyncTask<CourseEntity, Void, Void> {
        private CourseDao dao;

        private UpdateAsyncCourse(CourseDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CourseEntity... courseEntities) {
            dao.update(courseEntities[0]);
            return null;
        }
    }
    private static class UpdateAsyncAssessment extends AsyncTask<AssessmentEntity, Void, Void> {
        private AssessmentDao dao;

        private UpdateAsyncAssessment(AssessmentDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(AssessmentEntity... assessmentEntities) {
            dao.update(assessmentEntities[0]);
            return null;
        }
    }
    private static class UpdateAsyncNote extends AsyncTask<NoteEntity, Void, Void> {
        private NoteDao dao;

        private UpdateAsyncNote(NoteDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            dao.update(noteEntities[0]);
            return null;
        }
    }

    private static class DeleteAsyncTerm extends AsyncTask<TermEntity, Void, Void> {
        private TermDao dao;

        private DeleteAsyncTerm(TermDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(TermEntity... termEntities) {
            dao.delete(termEntities[0]);
            return null;
        }
    }
    private static class DeleteAsyncCourse extends AsyncTask<CourseEntity, Void, Void> {
        private CourseDao dao;

        private DeleteAsyncCourse(CourseDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CourseEntity... courseEntities) {
            dao.delete(courseEntities[0]);
            return null;
        }
    }
    private static class DeleteAsyncAssessment extends AsyncTask<AssessmentEntity, Void, Void> {
        private AssessmentDao dao;

        private DeleteAsyncAssessment(AssessmentDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(AssessmentEntity... assessmentEntities) {
            dao.delete(assessmentEntities[0]);
            return null;
        }
    }
    private static class DeleteAsyncNote extends AsyncTask<NoteEntity, Void, Void> {
        private NoteDao dao;

        private DeleteAsyncNote(NoteDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            dao.delete(noteEntities[0]);
            return null;
        }
    }
}
