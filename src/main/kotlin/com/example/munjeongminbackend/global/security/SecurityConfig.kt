package com.example.munjeongminbackend.global.security

import com.example.munjeongminbackend.global.security.jwt.JwtProvider
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig (
        private val jwtProvider: JwtProvider,
        private val objectMapper: ObjectMapper
) {

    @Bean
    protected fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
                .formLogin().disable()
                .csrf().disable()
                .cors()

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http
                .authorizeHttpRequests()

                .antMatchers(HttpMethod.GET, "/login/oauth2/code/google").permitAll()
                .antMatchers(HttpMethod.POST, "/user/login").permitAll()

                .antMatchers(HttpMethod.POST, "/bucket").authenticated()
                .antMatchers(HttpMethod.GET, "/bucket").authenticated()
                .antMatchers(HttpMethod.PUT, "/bucket/{id}").authenticated()
                .antMatchers(HttpMethod.PUT, "/bucket/end/{id}").authenticated()
                .antMatchers(HttpMethod.DELETE, "/bucket/{id}").authenticated()

                .antMatchers(HttpMethod.POST, "/member/{id}").authenticated()

                .antMatchers(HttpMethod.POST, "/image").permitAll()

                .antMatchers(HttpMethod.GET, "/room").authenticated()
                .antMatchers(HttpMethod.GET, "/room/{id}").authenticated()

                .anyRequest().permitAll()

                .and()
                .apply(FilterConfig(jwtProvider, objectMapper))

        return http.build()
    }

}