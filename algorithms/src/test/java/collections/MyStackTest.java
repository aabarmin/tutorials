package collections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {
    @Test
    void shouldBeEmptyByDefault() {
        final MyStack<Integer> stack = new MyStack<>();
        assertTrue(stack.isEmpty());
    }

    @Test
    void shouldNotBeEmptyAfterAddingTheElement() {
        final MyStack<Integer> stack = new MyStack<>();
        stack.push(10);
        assertFalse(stack.isEmpty());
    }

    @Test
    void shouldBeEmptyAfterAddAndRemoveOfTheElement() {
        final MyStack<Integer> stack = new MyStack<>();
        stack.push(10);
        stack.pop();
        assertTrue(stack.isEmpty());
    }
}