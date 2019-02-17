package com.squirrel.courses;

import com.squirrel.courses.dataaccess.dao.course.CourseDAO;
import com.squirrel.courses.dataaccess.dao.lesson.LessonDAO;
import com.squirrel.courses.dataaccess.model.Lesson;
import com.squirrel.courses.service.lesson.ILessonService;
import com.squirrel.courses.service.lesson.LessonService;
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
 * Class LessonServiceTest realizes methods to test Lesson Service Layer.
 *
 * @author    Maxim Tytskiy
 */

@RunWith(SpringRunner.class)
public class LessonServiceTest {

    @TestConfiguration
    static class LessonServiceTestContextConfiguration {
        @Bean
        public ILessonService lessonService() {
            return new LessonService();
        }
    }

    @Autowired
    private ILessonService lessonService;

    @MockBean
    private LessonDAO lessonDAO;

    /**
     * Set up test data and configuration.
     */
    @Before
    public void setUp(){
        Lesson lessonOne = new Lesson(1000, 7, "testLessonOne",
                "TestOne description", "TestOne material");
        Lesson lessonTwo = new Lesson(1001, 7, "testLessonTwo",
                "TestTwo description", "TestTwo material");
        Lesson lessonThree = new Lesson(1002, 16, "TestLessonThree",
                "TestThree description", "TestThree material");


        List<Lesson> lessonListOneAndTwo = new ArrayList<>();
        lessonListOneAndTwo.add(lessonOne);
        lessonListOneAndTwo.add(lessonTwo);

        List<Lesson> lessonListThree = new ArrayList<>();
        lessonListThree.add(lessonThree);

        Mockito.when(lessonDAO.getLessonByID(lessonOne.getId())).thenReturn(lessonOne);

        Mockito.when(lessonDAO.allLessonsByCourse(lessonOne.getCourse())).thenReturn(lessonListOneAndTwo);
        Mockito.when(lessonDAO.allLessonsByCourse(lessonThree.getCourse())).thenReturn(lessonListThree);
    }

    /**
     * Try to find lesson by valid id.
     */
    @Test
    public void whenValidIdThenLessonFound() {
        int id = 1000;
        Lesson found = lessonService.getLessonById(id);

        assertThat(found.getId()).isEqualTo(id);
    }

    /**
     * Try to find lesson by non valid id.
     */
    @Test(expected = NullPointerException.class)
    public void whenNotValidIdThenLessonFound() {
        int id = 3000;
        Lesson found = lessonService.getLessonById(id);
    }

    /**
     * Try to find lessons by valid course.
     */
    @Test
    public void whenValidCourseThenLessonsFound(){
        int courseId = 7;
        List<Lesson> found = lessonService.getLessonsByCourse(courseId);

        assertThat(found.size()).isEqualTo(2);
        assertThat(found.get(0).getId()).isEqualTo(1000);
        assertThat(found.get(1).getId()).isEqualTo(1001);
    }
}
