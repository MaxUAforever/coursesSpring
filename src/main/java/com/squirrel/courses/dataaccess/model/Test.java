package com.squirrel.courses.dataaccess.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class Test represents the entity of test with main information about it.
 *
 * @author    Natalie Tkachenko
 */

@Entity
@Table( name="test" )
public class Test {
    @Id
    @Column(name = "id")
    private int id;

    /**
     * Var lesson keeps id of appropriate lesson.
     * But if test is exam, then it keeps id of course.
     */
    @Column(name = "lesson")
    private int lesson;

    /**
     * Var evaluation defines maximal count of points
     * which user can earn for answering all questions of this test.
     */
    @Column(name = "evaluation")
    private int evaluation;

    @Column(name = "isExam")
    private byte isExam;

    public Test() {
    }

    public Test(int id, int lesson, int evaluation, byte isExam) {
        this.id = id;
        this.lesson = lesson;
        this.evaluation = evaluation;
        this.isExam = isExam;
    }

    public Test(int lesson, int evaluation, byte isExam) {
        this.lesson = lesson;
        this.evaluation = evaluation;
        this.isExam = isExam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public byte getIsExam() {
        return isExam;
    }
}
