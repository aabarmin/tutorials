package ru.mydesignstudio.spring.mvc.resolver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;
import java.util.Locale;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(MyConfiguration.class)
class ResolversTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext webContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext)
                .alwaysDo(print())
                .build();
    }

    @Test
    void check_contextStarts() {
        assertNotNull(mockMvc);
    }

    @Test
    void index_returnDefaultLocale() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("locale", Locale.ENGLISH));
    }

    @Test
    void index_returnJapanLocale() throws Exception {
        final MockHttpServletRequestBuilder requestBuilder = get("/")
                .cookie(new Cookie("language", "ja_JP"));
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(model().attribute("locale", Locale.JAPAN));
    }

    @Test
    void index_getLocaleFromCookieAndThenChangeIt() throws Exception {
        final MockHttpServletRequestBuilder firstRequest = get("/")
                .cookie(new Cookie("language", "ja_JP"));

        mockMvc.perform(firstRequest)
                .andExpect(status().isOk())
                .andExpect(model().attribute("locale", Locale.JAPAN));

        final MockHttpServletRequestBuilder secondRequest = get("/")
                .requestAttr("changeLanguage", "en_EN");

        mockMvc.perform(secondRequest)
                .andExpect(status().isOk())
                .andExpect(model().attribute("locale", Locale.ENGLISH));
    }

    @Test
    void index_themeShouldNotBeNull() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("theme", is(notNullValue())));
    }

    @Test
    void index_tryToUploadFile() throws Exception {
        final MockMultipartHttpServletRequestBuilder requestBuilder = multipart("/")
                .file(new MockMultipartFile("file", "Upload file content".getBytes()));
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(model().attribute("upload.filename", "file"));
    }
}
