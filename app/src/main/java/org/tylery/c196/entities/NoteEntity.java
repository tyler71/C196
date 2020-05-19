package org.tylery.c196.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.tylery.c196.generics.GenericEntity;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "notes",
        foreignKeys = @ForeignKey(entity = CourseEntity.class,
                parentColumns = "id",
                childColumns = "courseID",
                onDelete = CASCADE))
public class NoteEntity extends GenericEntity {
    @PrimaryKey
    private int id;

    private int courseID;

    private String name;
    private String content;

    public NoteEntity(int courseID, String name, String content) {
        this.courseID = courseID;
        this.name = name;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
