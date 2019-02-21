package com.squirrel.courses.dataaccess.repositories;

import com.squirrel.courses.dataaccess.model.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<AppUser,Integer> {
    @Query(value = "SELECT u FROM AppUser u WHERE u.login = :login")
    AppUser findByLogin(@Param("login") String login);
}
