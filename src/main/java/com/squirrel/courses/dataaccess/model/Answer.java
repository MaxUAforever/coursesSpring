package com.squirrel.courses.dataaccess.model;

import javax.persistence.*;

/**
 * Class Answer represents the entity of answer to the question with main information about it.
 *
 * @author    Natalie Tkachenko
 */

@Entity
@Table(name="answer")
public class Answer {
    @Id
    @Column (name = "id")
    private int id;

    @Column (name="question")
    private int question;

    @Column (name="a_text")
    private String ansText;

    /** Var coef defines correctness of answer from 0(absolutely wrong) to 1(absolutely right). */
    @Column (name="coefficient")
    private double coef;

    public Answer(int id, int question, String ansText, double coef) {
        this.id = id;
        this.question = question;
        this.ansText = ansText;
        this.coef = coef;
    }

    public Answer(int question, String ansText, double coef) {
        this.question = question;
        this.ansText = ansText;
        this.coef = coef;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public String getAnsText() {
        return ansText;
    }

    public double getCoef() {
        return coef;
    }
}
