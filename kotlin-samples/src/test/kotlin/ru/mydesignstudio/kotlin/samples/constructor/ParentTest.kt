package ru.mydesignstudio.kotlin.samples.constructor

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

internal class ParentTest {
    @Test
    internal fun parent_withoutArguments() {
        var parent = Parent()

        assertNotNull(parent)
        assertEquals("Default First Name", parent.firstName)
        assertEquals("Default Last Name", parent.lastName)
    }

    @Test
    internal fun parent_withArguments() {
        var parent = Parent("First Name")

        assertNotNull(parent)
        assertEquals("First Name", parent.firstName)
        assertEquals("Default Last Name", parent.lastName)
    }

    @Test
    internal fun parent_withNamedArguments() {
        var parent = Parent(lastName = "Last Name")

        assertNotNull(parent)
        assertEquals("Default First Name", parent.firstName)
        assertEquals("Last Name", parent.lastName)
    }
}
