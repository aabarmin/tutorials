package ru.mydesignstudio.spring.test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import ru.mydesignstudio.spring.test.web.WebConfiguration;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Transactional
@SpringJUnitWebConfig(WebConfiguration.class)
@Sql("classpath:create-tables.sql")
public class PersonHtmlUnitTest {
    private MockMvc mockMvc;
    private WebClient webClient;

    @BeforeEach
    public void setup(WebApplicationContext applicationContext) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .alwaysDo(print())
                .build();

        webClient = MockMvcWebClientBuilder
                .mockMvcSetup(mockMvc)
                .build();
    }

    @Test
    public void mvc_findAll() throws Exception {
        final HtmlPage page = webClient.getPage("http://localhost/person");
        final DomElement pageTitle = page.getElementsByTagName("h1").iterator().next();

        assertAll(
                () -> assertNotNull(pageTitle),
                () -> assertTrue(pageTitle.getTextContent().contains("People"))
        );
    }

    @Test
    public void mvc_createNew() throws Exception {
        final HtmlPage page = webClient.getPage("http://localhost/person");
        final HtmlForm createNewPersonForm = page.getHtmlElementById("create-new-person-form");

        assertNotNull(createNewPersonForm);

        final HtmlTextInput firstNameInput = createNewPersonForm.getOneHtmlElementByAttribute("input", "name", "firstName");
        final HtmlTextInput lastNameInput = createNewPersonForm.getOneHtmlElementByAttribute("input", "name", "lastName");
        final HtmlButton submitButton = createNewPersonForm.getOneHtmlElementByAttribute("button", "type", "submit");

        firstNameInput.setText("First Name");
        lastNameInput.setText("Last Name");

        final HtmlPage theSamePage = submitButton.click();

        assertNotNull(theSamePage);

        final HtmlTable peopleTable = theSamePage.getHtmlElementById("people-list-table");
        peopleTable.getRows().stream()
                .filter(row -> this.rowHasCellWithValue(row, "First Name"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Can't find row"));
    }

    private boolean rowHasCellWithValue(HtmlTableRow row, String cellValue) {
        return row.getCells().stream()
                .filter(cell -> cell.getTextContent().contains(cellValue))
                .findFirst()
                .isPresent();
    }
}
