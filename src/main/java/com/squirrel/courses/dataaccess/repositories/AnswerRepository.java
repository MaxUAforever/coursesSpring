package com.squirrel.courses.dataaccess.repositories;


import com.squirrel.courses.dataaccess.model.Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answer,Integer> {

    @Query(value = "SELECT a FROM Answer a WHERE a.question = :quest")
    List<Answer> findAnswersByQuestion(@Param("quest") int quest);

}
