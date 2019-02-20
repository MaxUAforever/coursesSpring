package com.squirrel.courses.service.course;


import com.squirrel.courses.dataaccess.model.Course;
import com.squirrel.courses.dataaccess.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAllCourses();
    }

    @Override
    public boolean addCourse(Course course){
        return (courseRepository.save(course) != null);
    }

    @Override
    public boolean deleteCourse(int id){
       Course course = courseRepository.getCourseById(id);
        courseRepository.delete(course);
        return true;
    }

    @Override
    public Course getCourseById(int id){
        return courseRepository.getCourseById(id);
    }

    @Override
    public List<String> getAllThemes(){
        return courseRepository.getAllThemes();
    }

    @Override
    public List<String> getLecturerCourseThemes(String lecturer){
        return courseRepository.getLecturerCourseThemes(lecturer);
    }

    @Override
    public List<Course> getCoursesByName(String name){
        return courseRepository.getCoursesByName(name);
    }

    @Override
    public List<Course> getCoursesByTheme(String theme){
        return courseRepository.getCoursesByTheme(theme);
    }

    @Override
    public List<Course> getCoursesByLecturer(String lecturer) {
        return courseRepository.getCoursesByLecturer(lecturer);
    }
}
