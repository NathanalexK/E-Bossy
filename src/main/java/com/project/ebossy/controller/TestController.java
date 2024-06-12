package com.project.ebossy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
@CrossOrigin("*")
public class TestController
{
    @GetMapping
    public ModelAndView sayHello()
    {
        ModelAndView modelAndView = new ModelAndView("test");
        return modelAndView;
    }
}
