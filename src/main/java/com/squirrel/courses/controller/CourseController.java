package com.squirrel.courses.controller;

import com.squirrel.courses.dataaccess.model.Course;
import com.squirrel.courses.dataaccess.model.Lesson;
import com.squirrel.courses.dataaccess.model.Test;
import com.squirrel.courses.service.course.ICourseService;
import com.squirrel.courses.service.test.ITestService;
import com.squirrel.courses.service.lesson.ILessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.*;

/**
 * Class CourseController realizes controller's methods related to working with course/courses.
 *
 * @author    Vladislava Prokopenko
*/
@Controller
public class CourseController {
    private static final String MESSAGE = "message";

    private ICourseService courseService;
    private ILessonService lessonService;
    private ITestService testService;

    @Autowired
    public CourseController(ILessonService lessonService, ICourseService courseService, ITestService testService){
        this.lessonService = lessonService;
        this.courseService = courseService;
        this.testService = testService;
    }

    /**
     * Controller method to get access and show information for course page.
     *
     * @param edit defines whether the page will be edited or not.
     */
    @GetMapping(value = {"/course"})
    public String showCourse(Model model, Principal principal, @RequestParam("courseId") int courseId,
                       @RequestParam("edit") Optional<Boolean> edit) {
        Course course = courseService.getCourseById(courseId);
        List<Lesson> lessons = lessonService.getLessonsByCourse(courseId);

        Map<Lesson, Test> testLesson = new TreeMap();
        for (Lesson lesson: lessons) {
            testLesson.put(lesson, testService.findTestByLesson(lesson.getId()));
        }

        boolean isEdit = false;
        if (course.getLecturer().equals(principal.getName()))
            isEdit = edit.orElse(false);

        model.addAttribute("exam", testService.findExamByCourse(courseId));
        model.addAttribute("edit", isEdit);
        model.addAttribute("isAuthor", course.getLecturer().equals(principal.getName()));
        model.addAttribute("testlessons", testLesson);
        model.addAttribute("course", course);
        return "course";
    }

    /**
     * Controller method to show page for adding new course.
     */
    @GetMapping("/addcourse")
    public String addCoursePage(){
        return "addcourse";
    }


    /**
     * Controller method to get access and show information about courses on page allcourses.
     */
    @RequestMapping({"/", "/allcourses"})
    public String showAllCourses(Model model, @RequestParam("courseName") Optional<String> courseName,
                                 @RequestParam("theme") Optional<String> theme){
        List<Course> courses;
        if((!courseName.isPresent()) && (!theme.isPresent())) {
            courses = courseService.getAllCourses();
        } else if(!theme.isPresent()) {
            courses = courseService.getCoursesByName(courseName.get());
        } else {
            courses = courseService.getCoursesByTheme(theme.get());
        }

        List<String> themes = courseService.getAllThemes();

        model.addAttribute("themes", themes);
        model.addAttribute("courses", courses);
        return "allcourses";
    }

    /**
     * Controller method to receive and post information from user about new course.
     */
    @PostMapping({"/postcourse"})
    public ModelAndView postNewCourse(ModelMap model, Principal principal, @RequestParam("courseTitle") String title, @RequestParam("theme") String theme,
                                      @RequestParam("description") String description) {

        boolean success = courseService.addCourse(new Course(principal.getName(), title, theme, description));

        if (success)
            model.addAttribute(MESSAGE, "Course is added!");
        else
            model.addAttribute(MESSAGE, "Course adding failed!");

        return new ModelAndView("redirect:/profile", model);
    }


    /**
     * Controller method to receive and post information from user about changes in edited course.
     */
    @PostMapping({"/editcourse"})
    public ModelAndView editCourse(ModelMap model, Principal principal, @RequestParam("courseId") int id,
                                   @RequestParam("courseTitle") String title,
                                   @RequestParam("theme") String theme,
                                   @RequestParam("description") String description) {
        Course course = new Course(id, principal.getName(), title, theme, description);
        boolean success = courseService.editCourse(course);

        if (success)
            model.addAttribute(MESSAGE, "Course is edited!");
        else
            model.addAttribute(MESSAGE, "Course editing failed!");

        return new ModelAndView("redirect:/course?courseId="+course.getId(), model);
    }


    /**
     * Controller method to receive query from user to delete course.
     */
    @PostMapping({"/deletecourse"})
    public ModelAndView deleteCourse(@RequestParam("courseId") int id){
        courseService.deleteCourse(id);

        return new ModelAndView("redirect:/profile");
    }

    /**
     * Controller method for page "about".
     */
    @GetMapping({"/about"})
    public String showAboutPage(){
        return "about";
    }
}
