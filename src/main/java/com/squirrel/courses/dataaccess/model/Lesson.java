package com.squirrel.courses.dataaccess.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class Lesson represents the entity of educational lecture with main information about it.
 *
 * @author    Vladislava Prokopenko
 */

@Entity
@Table (name="lesson")
public class Lesson implements Comparable<Lesson>{
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "course")
    private int course;

    @Column(name = "less_name")
    private String lessName;

    @Column(name = "description")
    private String description;

    @Column(name = "material")
    private String material;

    public Lesson(int id, int course, String lessName, String description, String material) {
        this.id = id;
        this.course = course;
        this.lessName = lessName;
        this.description = description;
        this.material = material;
    }

    public Lesson(int course, String lessName, String description, String material) {
        this.course = course;
        this.lessName = lessName;
        this.description = description;
        this.material = material;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getLessName() {
        return lessName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public int compareTo(Lesson lesson) {
        return Integer.compare(this.id, lesson.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Lesson)) {
            return false;
        }

        Lesson lesson = (Lesson)obj;
        return (this.id == lesson.id);
    }
}
