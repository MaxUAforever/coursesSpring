package com.squirrel.courses.dataaccess.repositories;

import com.squirrel.courses.dataaccess.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository <Course,Integer> {

    @Query(value = "SELECT c FROM Course c WHERE c.id = :id")
    Course findOne(@Param ("id") int id);

    @Query(value = "SELECT c FROM Course c ")
    List<Course> findAllCourses();

    @Query(value="SELECT DISTINCT t.theme FROM Course t")
    List<String> getAllThemes();

    @Query(value="SELECT c FROM Course c WHERE c.lecturer = :lecturer")
    List<Course> getLecturerCourses(@Param ("lecturer") String lecturer);

    @Query(value="SELECT DISTINCT t.theme FROM Course t WHERE t.lecturer = :lecturer")
    List<String> getLecturerCourseThemes(@Param("lecturer") String lecturer);

    @Query(value="SELECT c FROM Course c WHERE c.courseName LIKE :name% GROUP BY c.id")
    List<Course> getCoursesByName(@Param("name") String name);

    @Query(value="SELECT c FROM Course c WHERE c.theme = :theme")
    List<Course> getCoursesByTheme(@Param("theme") String theme);

}
