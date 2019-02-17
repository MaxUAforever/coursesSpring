package com.squirrel.courses;

import com.squirrel.courses.dataaccess.dao.course.CourseDAO;
import com.squirrel.courses.dataaccess.model.Course;
import com.squirrel.courses.service.course.CourseService;
import com.squirrel.courses.service.course.ICourseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Class CourseServiceTest realizes methods to test Course Service Layer.
 *
 * @author    Maxim Tytskiy
 */

@RunWith(SpringRunner.class)
public class CourseServiceTest {

    @TestConfiguration
    static class CourseServiceTestContextConfiguration {
        @Bean
        public ICourseService courseService() { return new CourseService();
        }
    }

    @Autowired
    private ICourseService courseService;

    @MockBean
    private CourseDAO courseDAO;

    /**
     * Set up data and configuration to test methods for all courses.
     */
    @Before
    public void setUpTestAllCourses(){
        Course courseOne = new Course(1000, "lecturerNameOne", "courseNameOne", "themeOne", "description");
        Course courseTwo = new Course(1001, "lecturerNameTwo", "courseNameTwo", "themeTwo", "description");

        List<Course> allCourses = new ArrayList<>();
        allCourses.add(courseOne);
        allCourses.add(courseTwo);

        List<String> themes = new ArrayList<>();
        for (Course course: allCourses) {
            themes.add(course.getTheme());
        }


        Mockito.when(courseDAO.findAllCourses()).thenReturn(allCourses);
        Mockito.when(courseDAO.findCoursesThemes()).thenReturn(themes);
    }

    /**
     * Try to find all courses.
     */
    @Test
    public void testGetAllCourses(){
        List<Course> found = courseService.getAllCourses();

        assertThat(found.size()).isEqualTo(2);
        assertThat(found.get(0).getId()).isEqualTo(1000);
        assertThat(found.get(1).getId()).isEqualTo(1001);
    }

    /**
     * Try to find all themes.
     */
    @Test
    public void testGetAllThemes(){
        List<String> theme = courseService.getAllThemes();

        assertThat(theme.size()).isEqualTo(2);
    }

    /**
     * Set up data and configuration to test methods for searching courses.
     */
    @Before
    public void setUpSearchTests()
    {
        Course course = new Course(1000, "lecturerNameOne", "courseNameOne", "themeOne", "description");

        List<Course> courses = new ArrayList<>();
        courses.add(course);

        Mockito.when(courseDAO.findCourseByID(course.getId())).thenReturn(course);
        Mockito.when(courseDAO.findCoursesByName(course.getCourseName())).thenReturn(courses);
        Mockito.when(courseDAO.findCoursesByTheme(course.getTheme())).thenReturn(courses);
        Mockito.when(courseDAO.findCoursesByLecturer(course.getLecturer())).thenReturn(courses);
    }

    /**
     * Try to find course by valid id.
     */
    @Test
    public void whenValidIdThenCourseFound(){
        int courseId = 1000;
        Course course = courseService.getCourseById(courseId);

        assertThat(course.getId()).isEqualTo(courseId);
    }

    /**
     * Try to find courses by valid name.
     */
    @Test
    public void whenValidNameThenCourseFound(){
        List<Course> courses = courseService.getCoursesByName("courseNameOne");

        assertThat(courses.size()).isEqualTo(1);
        assertThat(courses.get(0).getId()).isEqualTo(1000);
    }

    /**
     * Try to find courses by valid theme.
     */
    @Test
    public void whenValidThemeThenCourseFound(){
        List<Course> courses = courseService.getCoursesByTheme("themeOne");

        assertThat(courses.size()).isEqualTo(1);
        assertThat(courses.get(0).getId()).isEqualTo(1000);
    }

    /**
     * Try to find course by valid lecturer.
     */
    @Test
    public void whenValidLecturerThenCourseFound(){
        List<Course> courses = courseService.getCoursesByLecturer("lecturerNameOne");

        assertThat(courses.size()).isEqualTo(1);
        assertThat(courses.get(0).getId()).isEqualTo(1000);
    }

    /**
     * Set up data and configuration to test methods for searching themes.
     */
    @Before
    public void setUpSearchThemes() {
        Course course = new Course(1000, "lecturerNameOne", "courseNameOne", "themeOne", "description");

        List<String> lecturerThemes = new ArrayList<>();
        lecturerThemes.add(course.getTheme());
        Mockito.when(courseDAO.findCoursesThemesByLecturer(course.getLecturer())).thenReturn(lecturerThemes);
    }

    /**
     * Try to find themes by valid lecturer.
     */
    @Test
    public void whenValidLecturerThenThemeFound(){
        List<String> themes = courseService.getLecturerCourseThemes("lecturerNameOne");

        assertThat(themes.size()).isEqualTo(1);
        assertThat(themes.get(0)).isEqualTo("themeOne");
    }
}
