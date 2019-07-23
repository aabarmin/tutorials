package ru.mydesignstudio.spring.mvc.model.attribute;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/model/attribute")
public class ModelAttributeController {
    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("common", "added");
    }

    @GetMapping("")
    public ModelAndView index(ModelAndView modelAndView) {
        return modelAndView;
    }
}
