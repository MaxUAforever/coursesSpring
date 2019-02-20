package com.squirrel.courses.dataaccess.repositories;

import com.squirrel.courses.dataaccess.model.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository <Question,Integer> {

    @Query(value = "SELECT q FROM Question q WHERE q.test = :test")
    List<Question> findQuestionsByTest(@Param("test")int test);

    @Query(value = "SELECT MAX(q.id) FROM Question q")
    int getLastQuestion();

}
