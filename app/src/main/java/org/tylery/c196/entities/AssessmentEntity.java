package org.tylery.c196.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "assessments",
        foreignKeys = @ForeignKey(entity = CourseEntity.class,
                parentColumns = "id",
                childColumns = "courseID",
                onDelete = CASCADE))
public class AssessmentEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int courseID;

    private String name;
    private String type;
    private String goalDate;
    private boolean alert;

    public AssessmentEntity(int courseID, String name, String type, String goalDate, boolean alert) {
        this.courseID = courseID;
        this.name = name;
        this.type = type;
        this.goalDate = goalDate;
        this.alert = alert;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGoalDate() {
        return goalDate;
    }

    public void setGoalDate(String goalDate) {
        this.goalDate = goalDate;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }
}
