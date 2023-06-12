package com.example.munjeongminbackend.global.socket

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig (
) : WebSocketMessageBrokerConfigurer {
    
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/ws/chat").setAllowedOriginPatterns("*")
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/queue", "/topic")
        registry.setApplicationDestinationPrefixes("/app")
    }
}

// https://accounts.google.com/o/oauth2/auth?client_id=465541274089-u777afbuec9desm0p4uu4qm1b8um0mcb.apps.googleusercontent.com&redirect_uri=http://localhost:8080/login/oauth2/code/google&response_type=code&scope=https://www.googleapis.com/auth/userinfo.profile%20https://www.googleapis.com/auth/userinfo.email