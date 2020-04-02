package ru.mydesignstudio.spring.mvc.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.context.ThemeSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyController {
    @Autowired
    private LocaleResolver localeResolver;
    @Autowired
    private ThemeSource themeSource;

    @GetMapping("/")
    public ModelAndView index(HttpServletRequest request) {
        final ModelAndView modelAndView = new ModelAndView("resolver/index");
        modelAndView.addObject("locale", localeResolver.resolveLocale(request));
        modelAndView.addObject("theme", themeSource.getTheme("themeName"));
        return modelAndView;
    }

    @PostMapping("/")
    public ModelAndView upload(@RequestParam("file") MultipartFile multipartFile) {
        final ModelAndView modelAndView = new ModelAndView("resolver/index");
        modelAndView.addObject("upload.filename", multipartFile.getName());
        return modelAndView;
    }
}
