package com.squirrel.courses.dataaccess.model;

import javax.persistence.*;

/**
 * Class Question represents the entity of question from test with main information about it.
 *
 * @author    Natalie Tkachenko
 */
@Entity
@Table (name="question")
public class Question implements Comparable<Question>{
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "test")
    private int test;

    @Column(name = "q_text")
    private String questText;

    /** Var points defines maximal count of points which user can earn for answering this question. */
   @Column(name = "points")
    private int points;

    /** Var isOpen defines the format of question.
     * Value 0 for close format with ready-made answer options.
     * Value 1 for open format.
     */
    @Column(name = "isOpen")
    private byte isOpen;

    public Question(int id, int test, String questText, int points, byte isOpen) {
        this.id = id;
        this.test = test;
        this.questText = questText;
        this.points = points;
        this.isOpen = isOpen;
    }

    public Question(int test, String questText, int points, byte isOpen) {
        this.test = test;
        this.questText = questText;
        this.points = points;
        this.isOpen = isOpen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public String getQuestText() {
        return questText;
    }

    public int getPoints() {
        return points;
    }

    public byte getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(byte isOpen) {
        this.isOpen = isOpen;
    }

    @Override
    public int compareTo(Question question) {
        return Integer.compare(this.id, question.getId());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Question)) {
            return false;
        }

        Question question = (Question) obj;
        return (this.id == question.id);
    }
}
