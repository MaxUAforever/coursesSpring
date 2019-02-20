package com.squirrel.courses.service.test;

import com.squirrel.courses.dataaccess.model.Answer;
import com.squirrel.courses.dataaccess.model.Question;
import com.squirrel.courses.dataaccess.model.Test;

import java.util.List;

public interface ITestService {
    Test findTestById(int id);
    Test findTestByLesson(int lesson);
    Test findExamByCourse(int course);
    boolean addTest(Test test);
    boolean deleteTest(int testId);
    int getLastTest();

    List<Question> findQuestionsByTest(int test);
    boolean addQuestion(Question quest);
    int getLastQuestion();

    List<Answer> findAnswersByQuestion(int quest);
    boolean addAnswer(Answer ans);
}
