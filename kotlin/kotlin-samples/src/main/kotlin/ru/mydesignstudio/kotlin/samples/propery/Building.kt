package ru.mydesignstudio.kotlin.samples.propery

class Building(
        _buildingId: Int,
        _address: BuildingAddress
) {
    val buildingId = _buildingId
        get() {
            println("Value has been got")
            return field
        }
    val address = _address
}
