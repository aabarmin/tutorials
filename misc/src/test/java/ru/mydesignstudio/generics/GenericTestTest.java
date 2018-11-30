package ru.mydesignstudio.generics;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GenericTestTest {
    @Test
    void genericTest() throws Exception {
        final GenericTest<Integer> integerGenericTest = new GenericTest<>();
        assertArrayEquals(new Type[0], integerGenericTest.getClass().getGenericInterfaces());
        assertEquals(Object.class, integerGenericTest.getClass().getGenericSuperclass());
        assertEquals(Object.class, integerGenericTest.getClass().getDeclaredField("value").getType());
        assertEquals(Object.class, integerGenericTest.getClass().getDeclaredMethod("getValue").getReturnType());
    }

    @Test
    void extendsGenericTest() throws Exception {
        final GenericTest<? extends Number> numberGeneric = new GenericTest<Integer>();
        assertArrayEquals(new Type[0], numberGeneric.getClass().getGenericInterfaces());
        assertEquals(Object.class, numberGeneric.getClass().getGenericSuperclass());
        assertEquals(Object.class, numberGeneric.getClass().getDeclaredField("value").getType());
        assertEquals(Object.class, numberGeneric.getClass().getDeclaredMethod("getValue").getReturnType());
    }

    @Test
    void superGenericTest() throws Exception {
        final GenericTest<? super Integer> numberGeneric = new GenericTest<Number>();
        assertArrayEquals(new Type[0], numberGeneric.getClass().getGenericInterfaces());
        assertEquals(Object.class, numberGeneric.getClass().getGenericSuperclass());
        assertEquals(Object.class, numberGeneric.getClass().getDeclaredField("value").getType());
    }
}