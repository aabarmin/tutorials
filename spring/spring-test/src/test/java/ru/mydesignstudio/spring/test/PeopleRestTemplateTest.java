package ru.mydesignstudio.spring.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import ru.mydesignstudio.spring.test.web.WebConfiguration;

import static org.springframework.test.web.client.ExpectedCount.times;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringJUnitWebConfig(WebConfiguration.class)
public class PeopleRestTemplateTest {
    private RestTemplate restTemplate;
    private MockRestServiceServer mockRestServiceServer;

    @BeforeEach
    public void setup() {
        restTemplate = new RestTemplate();
        mockRestServiceServer = MockRestServiceServer.bindTo(restTemplate).build();
        mockRestServiceServer.expect(times(1), requestTo("/somewhere")).andRespond(withSuccess());
    }

    @Test
    public void mock_restTest() {
        restTemplate.getForObject("/somewhere", String.class);

        mockRestServiceServer.verify();
    }
}
