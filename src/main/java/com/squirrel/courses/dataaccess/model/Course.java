package com.squirrel.courses.dataaccess.model;

import javax.persistence.*;

@Entity
@Table ( name="course" )
public class Course {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "lecturer")
    private String lecturer;

    @Column(name = "theme")
    private String theme;

    @Column(name = "description")
    private String description;

    public Course(int id, String lecturer, String courseName, String theme, String description) {
        this.id = id;
        this.lecturer = lecturer;
        this.courseName = courseName;
        this.theme = theme;
        this.description = description;
    }

    public Course() {
        this.id = 0;
        this.lecturer = "";
        this.courseName = "";
        this.theme = "";
        this.description = "";
    }

    public Course(String lecturer, String courseName, String theme, String description) {
        this.lecturer = lecturer;
        this.courseName = courseName;
        this.theme = theme;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
