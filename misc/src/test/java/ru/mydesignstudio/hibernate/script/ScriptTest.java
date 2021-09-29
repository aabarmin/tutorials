package ru.mydesignstudio.hibernate.script;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.mydesignstudio.hibernate.configuration.HibernateConfiguration;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        HibernateConfiguration.class
})
@Transactional
public class ScriptTest {
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    @EnabledIfEnvironmentVariable(named = "CI", matches = "true")
    public void createMultipleScripts() {
        final Session session = sessionFactory.getCurrentSession();

        // create the script, fill it with a collection of commands
        final int scriptId = new ScriptCreationJob(session).invoke();

        // clear the cache, flush the session
        session.flush();
        session.clear();

        // read script back
        new ScriptReadJob(session).invoke(scriptId);

        session.flush();
        session.clear();

        new ScriptCommandsMovementJob(session).invoke(scriptId);

        session.flush();
        session.clear();

        new ScriptReadJob(session).invoke(scriptId);
    }

    private String createRandomString() {
        return RandomStringUtils.random(20, true, false);
    }

    private class ScriptCreationJob {
        private Session session;

        public ScriptCreationJob(Session session) {
            this.session = session;
        }

        public int invoke() {
            // create initial sequence of commands
            final Script script = createScript();
            script.getCommands().add(createCommand(script));
            script.getCommands().add(createCommand(script));
            script.getCommands().add(createCommand(script));
            script.getCommands().add(createCommand(script));
            script.getCommands().add(createCommand(script));

            session.persist(script);

            assertAll(
                    () -> assertNotNull(script),
                    () -> assertTrue(script.getId() > 0),
                    () -> assertFalse(script.getCommands().isEmpty()),
                    () -> assertEquals(script.getCommands().size(), 5),
                    () -> assertArrayEquals(
                            script.getCommands().stream()
                                    .mapToInt(ScriptCommand::getOrder)
                                    .toArray(),
                            new int[]{1, 2, 3, 4, 5}
                    ),
                    () -> assertFalse(
                            script.getCommands().stream()
                                    .filter((ScriptCommand command) -> command.getId() == 0)
                                    .findAny()
                                    .isPresent(),
                            "Script Commands weren't saved, they have no id"
                    )
            );
            return script.getId();
        }

        private Script createScript() {
            final Script script = new Script();
            script.setTitle(createRandomString());
            return script;
        }

        private ScriptCommand createCommand(Script script) {
            final ScriptCommand command = new ScriptCommand();
            command.setOrder(script.getCommands().size() + 1);
            command.setScript(script);
            command.setTitle("Command " + command.getOrder());
            return command;
        }
    }

    private class ScriptReadJob {
        private final Session session;

        public ScriptReadJob(Session session) {
            this.session = session;
        }

        public void invoke(int scriptId) {
            final Script script = session.get(Script.class, scriptId);

            assertAll(
                    () -> assertNotNull(script),
                    () -> assertFalse(script.getCommands().isEmpty()),
                    () -> assertArrayEquals(
                            script.getCommands().stream()
                                    .mapToInt(ScriptCommand::getOrder)
                                    .toArray(),
                            new int[]{1, 2, 3, 4, 5}
                    )
            );
        }
    }

    private class ScriptCommandsMovementJob {
        private final Session session;

        public ScriptCommandsMovementJob(Session session) {
            this.session = session;
        }

        public void invoke(int scriptId) {
            final Script script = session.get(Script.class, scriptId);

            assertNotNull(script);

            final List<ScriptCommand> newCommands = new ArrayList<>();
            script.getCommands().stream()
                    .peek((ScriptCommand command) -> command.setScript(null))
                    .peek((ScriptCommand command) -> command.setId(0))
                    .forEach(newCommands::add);

            script.getCommands().clear();

            assertAll(
                    () -> assertFalse(newCommands.isEmpty()),
                    () -> assertTrue(script.getCommands().isEmpty())
            );

            session.flush();

            final Script newScript = session.get(Script.class, scriptId);
            newCommands.stream()
                    .peek((ScriptCommand command) -> command.setScript(newScript))
                    .peek((ScriptCommand command) -> newScript.getCommands().add(command))
                    .count();
        }
    }
}
