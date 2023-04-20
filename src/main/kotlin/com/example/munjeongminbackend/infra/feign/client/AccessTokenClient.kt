package com.example.munjeongminbackend.infra.feign.client

import com.example.munjeongminbackend.infra.feign.dto.UserAccessDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "AccessTokenClient", url = "https://oauth2.googleapis.com/token")
interface AccessTokenClient {

    @PostMapping
    fun access(
            @RequestParam(value = "code") code: String,
            @RequestParam(value = "client_id") clientId: String,
            @RequestParam(value = "client_secret") clientSecret: String,
            @RequestParam(value = "redirect_uri") redirectUri: String,
            @RequestParam(value = "grant_type") grantType: String
    ): UserAccessDto
}
