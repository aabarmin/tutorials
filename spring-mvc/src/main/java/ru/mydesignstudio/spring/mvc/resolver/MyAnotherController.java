package ru.mydesignstudio.spring.mvc.resolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/another")
public class MyAnotherController {
    @GetMapping("")
    public ModelAndView first() {
        final ModelAndView modelAndView = new ModelAndView("resolver/index");
        modelAndView.addObject("param", "value1");
        return modelAndView;
    }

    @GetMapping(value = "", params = "someParam=someValue")
    public ModelAndView second() {
        final ModelAndView modelAndView = new ModelAndView("resolver/index");
        modelAndView.addObject("param", "value2");
        return modelAndView;
    }

    @GetMapping("/{value}")
    public ModelAndView withComplexType(@PathVariable("value") MyComplexValue value) {
        final ModelAndView modelAndView = new ModelAndView("resolver/index");
        modelAndView.addObject("param", value.getValue());
        return modelAndView;
    }
}
