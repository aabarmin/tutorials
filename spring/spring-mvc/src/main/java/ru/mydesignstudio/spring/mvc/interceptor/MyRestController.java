package ru.mydesignstudio.spring.mvc.interceptor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MyRestController {
    @GetMapping("")
    public String index() {
        return "";
    }
}
