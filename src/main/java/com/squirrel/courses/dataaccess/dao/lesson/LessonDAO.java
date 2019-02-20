package com.squirrel.courses.dataaccess.dao.lesson;

import com.squirrel.courses.dataaccess.mapper.CourseMapper;
import com.squirrel.courses.dataaccess.mapper.LessonMapper;
import com.squirrel.courses.dataaccess.model.Course;
import com.squirrel.courses.dataaccess.model.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;

/**
 * Class LessonDAO realizes data-access methods related to working with table Lesson.
 *
 * @author    Vladislava Prokopenko
 */
@Repository
@Transactional
public class LessonDAO extends JdbcDaoSupport implements ILessonDAO {

    @Autowired
    public LessonDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public List<Lesson> allLessonsByCourse(int idCourse) {

        String sql = LessonMapper.BASE_SQL + " WHERE course = ?";

        Object[] params = new Object[]{idCourse};
        LessonMapper mapper = new LessonMapper();

        try {
            return getJdbcTemplate().query(sql, params, mapper);
        } catch (EmptyResultDataAccessException e){
            return Collections.emptyList();
        }
    }

    @Override
    public String getCourseName(int id) {
        String sql = CourseMapper.ID_SQL;

        Object[] params = new Object[]{id};
        CourseMapper mapper = new CourseMapper();

        try {
            Course course = getJdbcTemplate().queryForObject(sql, params, mapper);
            return course.getCourseName();
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public String getLessonName(int id){
        String sql = LessonMapper.ID_SQL;

        Object[] params = new Object[]{id};
        LessonMapper mapper = new LessonMapper();

        try {
            return getJdbcTemplate().queryForObject(sql, params, mapper).getLessName();
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }


    @Override
    public Lesson getLessonByID(int id) {
        String sql = LessonMapper.ID_SQL;

        Object[] params = new Object[]{id};
        LessonMapper mapper = new LessonMapper();

        try {
            return getJdbcTemplate().queryForObject(sql, params, mapper);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public boolean addLesson(Lesson lesson) {

        String sql = LessonMapper.INSERT_SQL + " VALUES(?, ?, ?, ? ,?)";

        Object[] params = new Object[]{lesson.getId(), lesson.getCourse(),
                lesson.getLessName(), lesson.getDescription(), lesson.getMaterial()};

        try {
            this.getJdbcTemplate().update(sql, params);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public boolean deleteLesson(int lessonId) {
        String sql = LessonMapper.DELETE_SQL + " WHERE id = ?";

        Object[] params = new Object[]{lessonId};
        try {
            this.getJdbcTemplate().update(sql, params);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

}
