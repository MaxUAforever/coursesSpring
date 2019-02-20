package com.squirrel.courses.service.course;

import com.squirrel.courses.dataaccess.model.Course;
import java.util.List;

public interface ICourseService {
    boolean addCourse(Course course);
    boolean editCourse(Course course);
    boolean deleteCourse(int id);
    Course getCourseById(int id);
    List<Course> getAllCourses();
    List<String> getAllThemes();
    List<String> getLecturerCourseThemes(String lecturer);
    List<Course> getCoursesByName(String name);
    List<Course> getCoursesByTheme(String theme);
    //List<Course> getCoursesByLecturer(String lecturer);
}
