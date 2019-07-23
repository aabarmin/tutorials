package ru.mydesignstudio.spring.mvc.session.scope;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/todo")
@SessionAttributes("todoList")
public class TodoSessionAttrController {
  @ModelAttribute("todoList")
  public TodoList todoList() {
    return new TodoList();
  }

  @GetMapping("")
  public String viewAll(Model model,
                        @ModelAttribute("todoList") TodoList todoList) {
    model.addAttribute("todos", todoList.findAll());
    return "todo/all";
  }

  @PostMapping("")
  public String addNew(Todo newTodo,
                       @ModelAttribute("todoList") TodoList todoList,
                       BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "todo/error";
    }
    todoList.add(newTodo);
    return "redirect:";
  }
}
