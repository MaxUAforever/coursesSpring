package com.squirrel.courses.service.test;

import com.squirrel.courses.dataaccess.model.Test;
import com.squirrel.courses.dataaccess.model.Answer;
import com.squirrel.courses.dataaccess.model.Question;
import com.squirrel.courses.dataaccess.repositories.AnswerRepository;
import com.squirrel.courses.dataaccess.repositories.QuestionRepository;
import com.squirrel.courses.dataaccess.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class TestService is a interlayer between controller and data-access classes for working with tests.
 *
 * @author    Natalie Tkachenko
 */

@Service
public class TestService implements ITestService{

    @Autowired
    private TestRepository stRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Test findTestById(int id){
        return stRepository.findTestById(id);
    }

    @Override
    public Test findTestByLesson(int lesson){
        return stRepository.findTestByLesson(lesson);
    }

    @Override
    public Test findExamByCourse(int course){
        return stRepository.findExamByCourse(course);
    }

    @Override
    public  boolean addTest(Test test){
        return (stRepository.save(test)!=null);
    }

    @Override
    public boolean deleteTest(int id){
        Test test = findTestById(id);
        if (test!=null){
            stRepository.delete(test);
            return true;
        }
        return false;
    }

    @Override
    public int getLastTest(){
        return stRepository.getLastTest();
    }

    @Override
    public List<Question> findQuestionsByTest(int test){
        return questionRepository.findQuestionsByTest(test);
    }

    @Override
    public boolean addQuestion(Question quest){
        if (questionRepository.save(quest)!=null) return true;
        return false;
    }

    @Override
    public int getLastQuestion(){
        return questionRepository.getLastQuestion();
    }

    public List<Answer> findAnswersByQuestion(int quest){
        return answerRepository.findAnswersByQuestion(quest);
    }

    public boolean addAnswer(Answer ans){
        if (answerRepository.save(ans)!=null) return true;
        return false;
    }
}
