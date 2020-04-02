package ru.mydesignstudio.spring.core.annotation.conditional;

import org.springframework.stereotype.Component;

@Component
@SomeAmazingCondition("first")
public class FirstDaoImpl implements SomeDao {
}
