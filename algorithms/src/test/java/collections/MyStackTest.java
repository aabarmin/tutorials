package collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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

    @Test
    void shouldBeEmptyAfterTwoElementsAddedAndRemoved() {
        final MyStack<Integer> stack = new MyStack<>();
        stack.push(10);
        stack.push(20);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }
}