package ru.mydesignstudio.spring.core.annotation.conditional;

import org.springframework.stereotype.Component;

@Component
@SomeAmazingCondition("second")
public class SecondDaoImpl implements SomeDao {
}
