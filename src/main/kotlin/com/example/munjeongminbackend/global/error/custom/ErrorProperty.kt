package com.example.munjeongminbackend.global.error.custom

interface ErrorProperty {
    fun status(): Int
    fun message(): String
}