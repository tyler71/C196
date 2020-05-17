package org.tylery.c196.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.tylery.c196.dao.AssessmentDao;
import org.tylery.c196.dao.CourseDao;
import org.tylery.c196.dao.NoteDao;
import org.tylery.c196.dao.TermDao;
import org.tylery.c196.entities.AssessmentEntity;
import org.tylery.c196.entities.CourseEntity;
import org.tylery.c196.entities.NoteEntity;
import org.tylery.c196.entities.TermEntity;

@Database(version = 1,
          entities = {
              TermEntity.class,
              CourseEntity.class,
              NoteEntity.class,
              AssessmentEntity.class
          })
public abstract class C196Database extends RoomDatabase {
    private static C196Database instance;

    public abstract TermDao termDao();
    public abstract CourseDao courseDao();
    public abstract NoteDao noteDao();
    public abstract AssessmentDao assessmentDao();

    public static synchronized C196Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    C196Database.class, "C196_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
