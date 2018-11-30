package ru.mydesignstudio.generics;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GenericExtendTestTest {
    @Test
    void defaultTest() throws Exception {
        final GenericExtendTest<Integer> integerGenericTest = new GenericExtendTest<>();
        assertArrayEquals(new Type[0], integerGenericTest.getClass().getGenericInterfaces());
        assertEquals(Object.class, integerGenericTest.getClass().getGenericSuperclass());
        assertEquals(Number.class, integerGenericTest.getClass().getDeclaredField("value").getType());
        assertEquals(Number.class, integerGenericTest.getClass().getDeclaredMethod("getValue").getReturnType());
    }
}