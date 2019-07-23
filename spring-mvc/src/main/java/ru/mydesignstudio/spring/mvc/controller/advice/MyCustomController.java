package ru.mydesignstudio.spring.mvc.controller.advice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class MyCustomController {
    @GetMapping
    public String indexView(Model model) {
        return "indexView";
    }

    @GetMapping("exception")
    public String throwsException() {
        throw new RuntimeException("Something has happened");
    }

    @GetMapping("parameterized")
    public ModelAndView withParameter(@RequestParam("value") String value) {
        final ModelAndView modelAndView = new ModelAndView("parameterizedView");
        modelAndView.addObject("valueAttribute", value);
        return modelAndView;
    }
}
