package ru.mydesignstudio.kotlin.samples.constructor

class Child(firstName: String) : Parent(firstName), HasAge {
    private var _age = 10

    override var age: Int
        get() = _age
        set(value) {
            _age = value
        }

    constructor(): this("No first name in child")

    constructor(age: Int): this() {
        this._age = age
    }
}
