package com.example.munjeongminbackend.global.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.google")
class ClientProperties (
        val clientId: String,
        val clientSecret: String,
        val redirectUri: String,
        val authorizationGrantType: String
)