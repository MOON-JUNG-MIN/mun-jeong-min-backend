package com.example.munjeongminbackend.infra.feign.client

import com.example.munjeongminbackend.infra.feign.dto.UserInfoDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "UserInfoClient", url = "https://www.googleapis.com/oauth2/v1/userinfo")
interface UserInfoClient {

    @GetMapping
    fun info(
            @RequestHeader("Authorization") access: String
    ): UserInfoDto

}