package ru.mydesignstudio.spring.mvc.controller.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class MyCustomControllerAdvice {
    @ModelAttribute("currentUser")
    public String currentUserName() {
        return "User Name";
    }

    @ExceptionHandler(RuntimeException.class)
    public String errorHandler(RuntimeException thrown, Model model) {
        model.addAttribute("errorMessage", thrown.getMessage());
        return "errorView";
    }
}
