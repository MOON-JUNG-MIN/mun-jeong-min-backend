package com.example.munjeongminbackend.domain.user.present

import com.example.munjeongminbackend.domain.user.present.dto.CodeRequest
import com.example.munjeongminbackend.domain.user.present.dto.TokenResponse
import com.example.munjeongminbackend.domain.user.service.LoginService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController (
        private val loginService: LoginService
) {

    @PostMapping("/login")
    fun login(@RequestBody codeRequest: CodeRequest): TokenResponse {
        return loginService.login(codeRequest)
    }

}