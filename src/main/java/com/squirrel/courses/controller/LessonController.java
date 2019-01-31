package com.squirrel.courses.controller;
//meow
import com.squirrel.courses.dataaccess.model.*;
import com.squirrel.courses.service.course.ITestService;
import com.squirrel.courses.service.lesson.ILessonService;
import com.squirrel.courses.service.course.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;


@Controller
public class LessonController {
    private ILessonService lessonService;
    private ICourseService courseService;

    @Autowired
    public LessonController(ILessonService lessonService, ICourseService courseService, ITestService testService){
        this.lessonService = lessonService;
        this.courseService = courseService;
    }

    @GetMapping(value = {"/lesson"})
    public String test(Model model, @RequestParam(value = "idLesson", required = false) int id) {
        int lessonId = id;

        Lesson lesson = lessonService.getLessonById(lessonId);
        model.addAttribute("lesson", lesson);
        return "lesson";
    }

    @GetMapping({"/addlesson"})
    public String addlesson(Model model, @RequestParam("courseId") int courseId) {
        Course course = courseService.getCourseById(courseId);
        model.addAttribute("course", course);
        return "addlesson";
    }

    @PostMapping({"/postlesson"})
    public ModelAndView postNewLesson(ModelMap model, @RequestParam("courseId") int courseId, @RequestParam("lessName") String lessName, @RequestParam("description") String description,
                                      @RequestParam("material") String material) {
        Lesson lesson = new Lesson(courseId, lessName, description, material);
        boolean success = lessonService.addLesson(lesson);

        if (success)
            model.addAttribute("message", "Lesson is added!");
        else
            model.addAttribute("message", "Lesson adding failed!");

        return new ModelAndView("redirect:/course?courseId=" + courseId, model);
    }
}
