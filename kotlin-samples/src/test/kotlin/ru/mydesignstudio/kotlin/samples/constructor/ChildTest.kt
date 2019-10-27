package ru.mydesignstudio.kotlin.samples.constructor

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

internal class ChildTest {
    @Test
    internal fun child_withoutArguments() {
        var child = Child()

        assertNotNull(child)
        assertEquals("No first name in child", child.firstName)
        assertEquals("Default Last Name", child.lastName)
    }

    @Test
    internal fun child_withArgument() {
        var child = Child("My custom first name")

        assertNotNull(child)
        assertEquals("My custom first name", child.firstName)
        assertEquals("Default Last Name", child.lastName)
        assertEquals(10, child.age)
    }

    @Test
    internal fun child_overloadedConstructor() {
        var child = Child(20)

        assertEquals(20, child.age)

        child.age = 30

        assertEquals(30, child.age)
    }
}
