package ru.mydesignstudio.spring.mvc.session.scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo")
public class TodoScopedController {
  @Autowired
  private TodoList todoList;

  @GetMapping("")
  public String viewAll(Model model) {
    model.addAttribute("todos", todoList.findAll());
    return "todo/all";
  }

  @PostMapping("")
  public String addNew(Todo newTodo,
                       BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "todo/error";
    }
    todoList.add(newTodo);
    return "redirect:";
  }
}
