package com.example.munjeongminbackend.global.security

import com.example.munjeongminbackend.global.error.ExceptionHandler
import com.example.munjeongminbackend.global.security.jwt.JwtFilter
import com.example.munjeongminbackend.global.security.jwt.JwtProvider
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class FilterConfig (
        private val jwtProvider: JwtProvider,
        private val objectMapper: ObjectMapper
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
    override fun configure(builder: HttpSecurity?) {
        val jwtFilter = JwtFilter(jwtProvider)
        val exceptionFilter = ExceptionHandler(objectMapper)
        builder?.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
        builder?.addFilterBefore(exceptionFilter, jwtFilter::class.java)
    }
}