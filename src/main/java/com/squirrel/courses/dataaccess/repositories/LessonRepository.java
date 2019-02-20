package com.squirrel.courses.dataaccess.repositories;


import com.squirrel.courses.dataaccess.model.Lesson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends CrudRepository<Lesson,Integer> {
    @Query(value = "SELECT l FROM Lesson l WHERE l.course = :course")
    List<Lesson> allLessonsByCourse(@Param("course") int course);

    @Query(value = "SELECT l FROM Lesson l WHERE l.id = :id")
    Lesson getLessonByID(@Param("id")  int id);
}
