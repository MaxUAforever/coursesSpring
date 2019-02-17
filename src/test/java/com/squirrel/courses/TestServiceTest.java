package com.squirrel.courses;

import com.squirrel.courses.dataaccess.dao.test.AnswerDAO;
import com.squirrel.courses.dataaccess.dao.test.QuestionDAO;
import com.squirrel.courses.dataaccess.dao.test.TestDAO;
import com.squirrel.courses.service.test.ITestService;
import com.squirrel.courses.service.test.TestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Class TestServiceTest realizes methods to test Test Service Layer.
 *
 * @author    Maxim Tytskiy
 */
@RunWith(SpringRunner.class)
public class TestServiceTest {

    @TestConfiguration
    static class CourseServiceTestContextConfiguration {
        @Bean
        public ITestService testService() { return new TestService();
        }
    }

    @Autowired
    private ITestService testService;

    @MockBean
    private TestDAO testDAO;

    @MockBean
    private QuestionDAO questionDAO;

    @MockBean
    private AnswerDAO answerDAO;

    /**
     * Set up test data and configuration.
     */
    @Before
    public void setUp(){
        com.squirrel.courses.dataaccess.model.Test test = new com.squirrel.courses.dataaccess.model.Test(1000, 10, 100, (byte)0);
        com.squirrel.courses.dataaccess.model.Test exam = new com.squirrel.courses.dataaccess.model.Test(1000, 10, 100, (byte)1);

        Mockito.when(testDAO.findTestById(test.getId())).thenReturn(test);
        Mockito.when(testDAO.findTestByLesson(test.getLesson())).thenReturn(test);
        Mockito.when(testDAO.findExamByCourse(exam.getLesson())).thenReturn(exam);
    }

    /**
     * Try to find test by valid id.
     */
    @Test
    public void whenValidIdThenTestFound(){
        int id = 1000;
        com.squirrel.courses.dataaccess.model.Test found = testService.findTestById(id);

        assertThat(found.getId()).isEqualTo(id);
    }

    /**
     * Try to find test by valid lesson.
     */
    @Test
    public void whenValidLessonThenTestFound(){
        int lesson = 10;
        com.squirrel.courses.dataaccess.model.Test found = testService.findTestByLesson(lesson);

        assertThat(found.getLesson()).isEqualTo(lesson);
    }

    /**
     * Try to find exam by valid course.
     */
    @Test
    public void whenValidCourseThenExamFound(){
        int course = 10;
        com.squirrel.courses.dataaccess.model.Test found = testService.findExamByCourse(course);

        assertThat(found.getLesson()).isEqualTo(course);
    }
}
