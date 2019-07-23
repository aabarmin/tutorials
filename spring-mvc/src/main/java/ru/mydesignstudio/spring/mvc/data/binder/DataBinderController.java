package ru.mydesignstudio.spring.mvc.data.binder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class DataBinderController {
    @Autowired
    private ConversionService conversionService;

    @InitBinder
    public void initDataBinder(WebDataBinder dataBinder) {
        dataBinder.setConversionService(conversionService);
    }

    @GetMapping("")
    public ModelAndView index(@RequestParam("unparsedDate") CustomComplexType complexType) {
        final ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("month", complexType.getMonth());
        modelAndView.addObject("year", complexType.getYear());
        modelAndView.addObject("day", complexType.getDay());
        return modelAndView;
    }
}
