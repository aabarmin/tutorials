package ru.mydesignstudio.spring.mvc.data.binder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/exception")
public class ExceptionHandlerController {
    @GetMapping("")
    public String methodThrowsException() {
        throw new UnsupportedOperationException();
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ModelAndView unsupportedOperationExceptionHandler() {
        final ModelAndView modelAndView = new ModelAndView("exceptionView");
        return modelAndView;
    }
}
