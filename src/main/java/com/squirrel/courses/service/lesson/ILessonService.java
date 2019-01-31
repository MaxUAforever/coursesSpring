package com.squirrel.courses.service.lesson;
//meow
import com.squirrel.courses.dataaccess.model.Lesson;
import com.squirrel.courses.dataaccess.model.Course;
import java.util.List;

public interface ILessonService {
    List<Lesson> getLessonsByCourse(int course);
    boolean addLesson(Lesson lesson);
    Lesson getLessonById(int lessonId);
}