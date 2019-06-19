package ru.mydesignstudio.spring.test;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SpringMocksTest {
    @Test
    void check_mockServletRequest() throws Exception {
        final HttpServletRequest servletRequest = new MockHttpServletRequest();
        final HttpServletResponse servletResponse = new MockHttpServletResponse();

        final HttpServlet unitUnderTest = new TestHttpServlet();

        ((TestHttpServlet) unitUnderTest).doGet(servletRequest, servletResponse);

        assertAll(
                () -> assertNotNull(servletRequest),
                () -> assertNotNull(servletResponse)
        );
    }

    static class TestHttpServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        }
    }
}
