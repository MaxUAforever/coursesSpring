package com.squirrel.courses.controller;

import com.squirrel.courses.dataaccess.model.*;
import com.squirrel.courses.service.test.ITestService;
import com.squirrel.courses.service.lesson.ILessonService;
import com.squirrel.courses.service.course.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * Class LessonController realizes controller's methods working with lesson/lessons.
 *
 * @author    Maxim Tytskiy, Vladislava Prokopenko
 */
@Controller
public class LessonController {
    private static final String LESSON = "lesson";
    private static final String COURSE = "course";
    private static final String MESSAGE = "message";

    private ILessonService lessonService;
    private ICourseService courseService;

    @Autowired
    public LessonController(ILessonService lessonService, ICourseService courseService, ITestService testService){
        this.lessonService = lessonService;
        this.courseService = courseService;
    }

    /**
     * Controller method to show information of lesson.
     */
    @GetMapping(value = {"/lesson"})
    public String test(Model model, @RequestParam("lessonId") int id) {

        Lesson lesson = lessonService.getLessonById(id);
        model.addAttribute(LESSON, lesson);
        return LESSON;
    }

    /**
     * Controller method to show page for adding new lesson.
     */
    @GetMapping({"/addlesson"})
    public String addlesson(Model model, @RequestParam("courseId") int courseId) {
        Course course = courseService.getCourseById(courseId);
        model.addAttribute(COURSE, course);
        return "addlesson";
    }

    /**
     * Controller method to get access and show information for course page.
     */
    @GetMapping({"/editlesson"})
    public String editlesson(Model model, @RequestParam("courseId") int courseId,
                             @RequestParam("lessonId") int lessonId)
    {
        Course course = courseService.getCourseById(courseId);

        model.addAttribute(COURSE, course);
        model.addAttribute(LESSON, lessonService.getLessonById(lessonId));
        return "editlesson";
    }

    /**
     * Controller method to receive and post information from user about new lesson.
     */
    @PostMapping({"/postlesson"})
    public ModelAndView postNewLesson(ModelMap model, @RequestParam("courseId") int courseId,
                                      @RequestParam("lessId") Optional<Integer> lessonId,
                                      @RequestParam("lessName") String lessName,
                                      @RequestParam("description") String description,
                                      @RequestParam("material") String material) {
        if (lessonId.isPresent()){
            lessonService.deleteLesson(lessonId.get());
        }

        Lesson lesson = new Lesson(courseId, lessName, description, material);
        boolean success = lessonService.addLesson(lesson);

        if (success)
            model.addAttribute(MESSAGE, "Lesson is added!");
        else
            model.addAttribute(MESSAGE, "Lesson adding failed!");

        return new ModelAndView("redirect:/course?courseId=" + courseId, model);
    }

    /**
     * Controller method to receive query from user to delete lesson.
     */
    @PostMapping({"/deletelesson"})
    public ModelAndView deleteCourse(@RequestParam("courseId") int courseId, @RequestParam("lessonId") int lessonId){
        lessonService.deleteLesson(lessonId);

        return new ModelAndView("redirect:/course?courseId=" + courseId);
    }
}
