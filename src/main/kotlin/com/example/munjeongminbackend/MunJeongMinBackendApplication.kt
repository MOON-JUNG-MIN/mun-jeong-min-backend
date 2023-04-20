package com.example.munjeongminbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class MunJeongMinBackendApplication

fun main(args: Array<String>) {
    runApplication<MunJeongMinBackendApplication>(*args)
}