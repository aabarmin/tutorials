package ru.mydesignstudio.mentoring.find.spring.service.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mydesignstudio.mentoring.find.spring.command.FileCommandResult;
import ru.mydesignstudio.mentoring.find.spring.command.FileCommandType;
import ru.mydesignstudio.mentoring.find.spring.configuration.GeneralConfiguration;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = GeneralConfiguration.class)
class AnalyzerAppImplTest {
    @Autowired
    private AnalyzerApp app;
    private File currentDirectory;

    @BeforeEach
    void setUp() {
        currentDirectory = new File(System.getProperty("user.home"));
    }

    @Test
    void applicationStarts() {
        assertNotNull(app);
    }

    @Test
    void testCommandReturnsResult() {
        final FileCommandResult result = app.runExecution(FileCommandType.FIND_FILE_WITH_MAX_S_NAME, currentDirectory);
        assertAll(
                () -> assertNotNull(result, "Result is NULL"),
                () -> assertNotNull(result.toString(), "Result wasn't provided")
        );
        System.out.println(result.toString());
    }
}
