package com.example.munjeongminbackend.global.error.custom

open class CustomException (
        private val errorProperty: ErrorProperty
) : RuntimeException() {

    val status: Int
        get() = errorProperty.status()

    val errorMessage: String
        get() = errorProperty.message()
}