package com.squirrel.courses.service.lesson;

import com.squirrel.courses.dataaccess.dao.lesson.ILessonDAO;
import com.squirrel.courses.dataaccess.model.Lesson;
import com.squirrel.courses.dataaccess.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Class LessonService is a interlayer between controller and data-access classes for working with lessons.
 *
 * @author    Vladislava Prokopenko
 */
@Service
public class LessonService implements ILessonService{

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public List<Lesson> getLessonsByCourse(int courseId){
        return lessonRepository.allLessonsByCourse(courseId);
    }

    @Override
    public void addLesson(Lesson lesson){
        lessonRepository.save(lesson);
    }

    @Override
    public Lesson getLessonById(int lessonId) {return lessonRepository.getLessonByID(lessonId);}

    @Override
    public void deleteLesson(int id) {
        lessonRepository.delete(lessonRepository.getLessonByID(id));
    }


}
