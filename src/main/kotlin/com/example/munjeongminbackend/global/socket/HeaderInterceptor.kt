package com.example.munjeongminbackend.global.socket

import com.example.munjeongminbackend.domain.chat.exception.ChatTokenNullException
import com.example.munjeongminbackend.domain.user.domain.repository.UserRepository
import com.example.munjeongminbackend.domain.user.exception.UserNotFoundException
import com.example.munjeongminbackend.global.security.jwt.JwtProperties
import com.example.munjeongminbackend.global.security.jwt.JwtProvider
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.ChannelInterceptor
import org.springframework.stereotype.Component

@Component
class HeaderInterceptor (
        private val jwtProvider: JwtProvider,
        private val userRepository: UserRepository
): ChannelInterceptor {

    override fun preSend(message: Message<*>, channel: MessageChannel): Message<*>? {

        val stompHeaderAccessor: StompHeaderAccessor =  StompHeaderAccessor.wrap(message)

        val token: String = stompHeaderAccessor.getNativeHeader("Authorization").toString()

        val email: String = jwtProvider.parseToken(token)
                ?: throw ChatTokenNullException.EXCEPTION

        val subject = jwtProvider.getTokenBody(email)
        val user = userRepository.findUserByEmail(subject) ?: throw UserNotFoundException.EXCEPTION

        return message
    }

}