package com.squirrel.courses.service.lesson;
//meow
import com.squirrel.courses.dataaccess.dao.lesson.ILessonDAO;
import com.squirrel.courses.dataaccess.model.Lesson;
import com.squirrel.courses.dataaccess.dao.course.ICourseDAO;
import com.squirrel.courses.dataaccess.model.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LessonService implements ILessonService{

    private ILessonDAO lessonDAO;
    private ICourseDAO courseDAO;

    @Autowired
    public void setLessonDAO(ILessonDAO lessonDAO, ICourseDAO courseDAO) {
        this.lessonDAO = lessonDAO;
        this.courseDAO = courseDAO;
    }

    @Override
    public List<Lesson> getLessonsByCourse(int courseId){
        return lessonDAO.allLessonsByCourse(courseId);
    }

    @Override
    public boolean addLesson(Lesson lesson){
        return lessonDAO.addLesson(lesson);
    }

    @Override
    public Lesson getLessonById(int lessonId) {return lessonDAO.getLessonByID(lessonId);}
}
