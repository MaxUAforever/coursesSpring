package com.squirrel.courses.dataaccess.dao.course;

import com.squirrel.courses.dataaccess.model.Course;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcCourseDAO implements CourseDAO{

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean addCourse(Course course) {
        String sql = "INSERT INTO course (lecturer, course_name, theme, description) VALUES (?, ?, ?, ?)";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, course.getLecturer());
            ps.setString(2, course.getCourseName());
            ps.setString(3, course.getTheme());
            ps.setString(4, course.getDescription());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

        return true;
    }

    @Override
    public boolean editCourse(Course course) {
        return false;
    }

    @Override
    public boolean deleteCourse(int id) {
        return false;
    }

    @Override
    public List<Course> findCoursesByLecturer(String lecturer) {
        String sql = "SELECT id, course_name, theme, description FROM course WHERE lecturer = ? ";
        Connection conn = null;
        ResultSet rs = null;
        List<Course> courseList = new ArrayList();
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, lecturer);

            rs = ps.executeQuery();

            Course course;
            while (rs.next()){
                course = new Course();
                course.setId(rs.getInt("id"));
                course.setCourseName(rs.getString("course_name"));
                course.setTheme(rs.getString("theme"));
                course.setDescription(rs.getString("description"));

                courseList.add(course);
            }

            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

        return courseList;
    }

    @Override
    public List<Course> findAllCourses() {
        return null;
    }

    @Override
    public List<Course> findCoursesByName(String name) {
        return null;
    }

    @Override
    public List<String> findCoursesThemes() {
        return null;
    }

    @Override
    public List<String> findCoursesThemesByLecturer(String lecturer) {
        return null;
    }

    @Override
    public Course findCourseByID(int id) {
        return null;
    }
}
