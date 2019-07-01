package ru.mydesignstudio.spring.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import ru.mydesignstudio.spring.data.model.Person;
import ru.mydesignstudio.spring.data.service.PeopleService;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(locations = "classpath:manual-transactions-context.xml")
@Sql("classpath:create-tables.sql")
public class ManualTransactionManagementTest {
    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private PeopleService peopleService;

    @Test
    void test_tryDeclarativeTransactionManagement() {
        final int before = peopleService.findAll().size();

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                peopleService.save(new Person(
                        "First Name",
                        "Last Name"
                ));
            }
        });

        final int after = peopleService.findAll().size();

        assertEquals(before + 1, after);
    }

    @Test
    void test_concurrentTransactions() {
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch firstLatch = new CountDownLatch(1);
        final CountDownLatch secondLatch = new CountDownLatch(1);
        final CountDownLatch thirdLatch = new CountDownLatch(1);

        final int before = peopleService.findAll().size();

        new Thread(() -> {
            await(startLatch);

            inTransaction(() -> {
                peopleService.save(new Person(
                        "First Name",
                        "Last Name"
                ));

                firstLatch.countDown();

                await(secondLatch);

                thirdLatch.countDown();
            });
        }).start();

        new Thread(() -> {
            await(startLatch);

            await(firstLatch);

            inTransaction(() -> {
                final int beforeCommit = peopleService.findAll().size();
                await(secondLatch);

                assertEquals(before, beforeCommit);
            });

            await(thirdLatch);

            inTransaction(() -> {
                final int afterCommit = peopleService.findAll().size();

                assertEquals(before + 1, afterCommit);
            });
        }).start();

        startLatch.countDown();
    }

    private void await(final CountDownLatch latch) {
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void inTransaction(final Runnable runnable) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                runnable.run();
            }
        });
    }
}
