package ru.mydesignstudio.spring.mvc.session.scope;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringJUnitWebConfig(MyConfiguration.class)
@ActiveProfiles("scoped")
class TodoScopedControllerTest {
  private MockMvc mockMvc;

  @BeforeEach
  void setup(WebApplicationContext webApplicationContext) {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .alwaysDo(print())
        .build();
  }

  @Test
  void viewAll_valueIsPresentInTheModel() throws Exception {
    mockMvc.perform(get("/todo"))
        .andExpect(status().isOk())
        .andExpect(model().attribute("todos", is(notNullValue())));
  }

  @Test
  void addNew_shouldBePresentInTheResults() throws Exception {
    mockMvc.perform(post("/todo")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("todo", "Something new")
    )
        .andExpect(status().is3xxRedirection());

    mockMvc.perform(get("/todo"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("todos"))
        .andExpect(model().attribute("todos", is(notNullValue())))
        .andExpect(model().attribute("todos", hasItem(new Todo("Something new"))));
  }

  @Test
  void addNew_shouldBeAddedAndThenReturned() throws Exception {
    final MvcResult mvcResult = mockMvc.perform(get("/todo"))
        .andExpect(status().isOk())
        .andExpect(model().attribute("todos", is(notNullValue())))
        .andReturn();

    final List<Todo> todos = (List<Todo>) mvcResult.getModelAndView().getModel().get("todos");

    mockMvc.perform(post("/todo")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("todo", "New todo item")
    )
        .andExpect(status().is3xxRedirection());

    mockMvc.perform(get("/todo"))
        .andExpect(status().isOk())
        .andExpect(view().name("todo/all"))
        .andExpect(model().attribute("todos", hasSize(todos.size())));
  }
}
