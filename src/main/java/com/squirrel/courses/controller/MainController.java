package com.squirrel.courses.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/"})
public class MainController {

    @GetMapping()
    public String main(Model model){
        return "index";
    }
}
