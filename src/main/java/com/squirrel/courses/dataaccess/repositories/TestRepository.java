package com.squirrel.courses.dataaccess.repositories;

import com.squirrel.courses.dataaccess.model.Test;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends CrudRepository<Test,Integer> {

    @Query(value="SELECT t FROM Test t WHERE t.id=:id")
    Test findTestById(@Param("id") int id);

    @Query(value="SELECT t FROM Test t WHERE t.isExam=0 AND t.lesson=:lesson")
    Test findTestByLesson(@Param("lesson") int lesson);

    @Query(value="SELECT t FROM Test t WHERE t.lesson=:course AND t.isExam=1")
    Test findExamByCourse(@Param("course") int course);

    @Query(value="SELECT MAX(t.id) FROM Test t")
    int getLastTest();
}
