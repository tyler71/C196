package org.tylery.c196.generics;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;


public interface GenericDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert( courseEntity);

    @Update
    void update(CourseEntity courseEntity);

    @Delete
    void delete(CourseEntity courseEntity);
}
