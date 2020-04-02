package ru.mydesignstudio.spring.test.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.mydesignstudio.spring.test.web.model.Person;
import ru.mydesignstudio.spring.test.web.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("")
    public ModelAndView viewAllPage() {
        final ModelAndView modelAndView = new ModelAndView("people/index");
        modelAndView.addObject("people", personService.findAll());
        return modelAndView;
    }

    @PostMapping("")
    public String createNewPerson(Person person) {
        personService.save(person);

        return "redirect:";
    }
}
