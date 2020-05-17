package org.tylery.c196.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "notes",
        foreignKeys = @ForeignKey(entity = CourseEntity.class,
                parentColumns = "id",
                childColumns = "courseID",
                onDelete = CASCADE))
public class NoteEntity {
    @PrimaryKey
    private int id;

    private int courseID;

    private String name;
    private String content;

}
