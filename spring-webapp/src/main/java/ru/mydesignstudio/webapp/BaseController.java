package ru.mydesignstudio.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class BaseController {
    @GetMapping
    public ModelAndView index() {
        final ModelAndView view = new ModelAndView("index");
        return view;
    }
}
