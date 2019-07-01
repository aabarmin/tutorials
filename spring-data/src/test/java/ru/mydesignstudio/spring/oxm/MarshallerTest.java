package ru.mydesignstudio.spring.oxm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@SpringJUnitConfig(locations = "classpath:unmarshaller-jaxb-context.xml")
public class MarshallerTest {
    @Autowired
    private Marshaller marshaller;

    @Test
    void check_marshaller() throws Exception {
        final Settings settings = new Settings(10, "Hello", "World");

        final StringWriter writer = new StringWriter();
        marshaller.marshal(settings, new StreamResult(writer));

        final String resultString = writer.toString();

        assertThat(resultString, containsString("<settings>"));
    }
}
