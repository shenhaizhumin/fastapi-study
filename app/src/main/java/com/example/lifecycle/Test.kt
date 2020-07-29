package com.example.lifecycle

class Test {

    class Person(var name: String)

    fun getPerson() {
        Person("zhangsan").apply {
            this.name = ""
        }
        val result = Person("lisi").let {
            it.name = ""
            200
        }

        val fun1: (a: String) -> Int
    }
}