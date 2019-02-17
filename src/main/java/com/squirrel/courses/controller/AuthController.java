package com.squirrel.courses.controller;

import com.squirrel.courses.dataaccess.model.AppUser;
import com.squirrel.courses.dataaccess.model.Course;
import com.squirrel.courses.service.course.ICourseService;
import com.squirrel.courses.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

/**
 * Class AuthController realizes controller's methods related to authorization process.
 *
 * @author    Bogdan Popovich
 */
@Controller
public class AuthController {
    private ICourseService courseService;
    private IUserService userService;

    @Autowired
    public AuthController(ICourseService courseService, IUserService userService){
        this.userService = userService;
        this.courseService = courseService;
    }

    /**
     * Controller method to show login page.
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    /**
     * Controller method which redirects user to different pages according to user's role.
     */
    @GetMapping({"/profile"})
    public String profilePage(Model model, Principal principal){
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        Collection<GrantedAuthority> authorities = loginedUser.getAuthorities();

        if (authorities != null){
            String auth = authorities.iterator().next().getAuthority();
            if (auth.equals("ROLE_ADMIN")){
                return "mockPage";
            }
            else if (auth.equals("ROLE_LECTURER")){
                return showLecturerInfo(model, principal);
            }
            else if (auth.equals("ROLE_STUDENT")){
                return "mockPage";
            }
        }
        return null;
    }

    /**
     * Controller method to show user's information if he's lecturer.
     */
    public String showLecturerInfo(Model model, Principal principal){
        List<Course> courses = courseService.getCoursesByLecturer(principal.getName());
        List<String> lecturerThemes = courseService.getLecturerCourseThemes(principal.getName());
        AppUser user = userService.findByLogin(principal.getName());

        String lecturerName = user.getUserName();
        String lecturerDesc = user.getDescription();
        model.addAttribute("specializations", lecturerThemes);
        model.addAttribute("courses", courses);
        model.addAttribute("lecturerName", lecturerName);
        model.addAttribute("lecturerDesc", lecturerDesc);

        return "lecturer";
    }
}
