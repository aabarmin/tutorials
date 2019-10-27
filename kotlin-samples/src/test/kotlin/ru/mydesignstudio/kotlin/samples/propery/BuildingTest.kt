package ru.mydesignstudio.kotlin.samples.propery

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

internal class BuildingTest {
    @Test internal fun building_create() {
        val building = Building(10, BuildingAddress(
                "Street",
                "Building",
                "App"
        ))

        assertNotNull(building)
        assertNotNull(building.address)
        assertNotNull(building.buildingId)

        assertNotNull(building.address.street)
        assertEquals("Street", building.address.street)
    }
}
