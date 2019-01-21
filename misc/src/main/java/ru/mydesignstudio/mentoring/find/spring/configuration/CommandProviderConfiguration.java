package ru.mydesignstudio.mentoring.find.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mydesignstudio.mentoring.find.spring.command.FileCommand;
import ru.mydesignstudio.mentoring.find.spring.command.FileCommandType;
import ru.mydesignstudio.mentoring.find.spring.command.impl.FindFileWithMaximumSCount;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class CommandProviderConfiguration {
    @Bean
    public FileCommand maxSFinder() {
        return new FindFileWithMaximumSCount();
    }

    @Bean
    public Map<FileCommandType, FileCommand> commands() {
        final Map<FileCommandType, FileCommand> commandMap = new EnumMap<FileCommandType, FileCommand>(FileCommandType.class);
        commandMap.put(FileCommandType.FIND_FILE_WITH_MAX_S_NAME, maxSFinder());
        return commandMap;
    }
}
