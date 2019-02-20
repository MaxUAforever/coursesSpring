package com.squirrel.courses.dataaccess.dao.lesson;

import com.squirrel.courses.dataaccess.model.Lesson;
import java.util.List;


/**
 * Interface ILessonDAO assigns data-access methods related to working with table Lesson.
 *
 * @author    Vladislava Prokopenko
 */
public interface ILessonDAO{

    List<Lesson> allLessonsByCourse(int idCourse);
    String getCourseName(int idCourse);
    Lesson getLessonByID(int id);
    //List<Lesson> getLessonsByName(String name);
    boolean addLesson(Lesson lesson);
    boolean deleteLesson(int id);
    String getLessonName(int id);
}
