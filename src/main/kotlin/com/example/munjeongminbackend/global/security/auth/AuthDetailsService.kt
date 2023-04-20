package com.example.munjeongminbackend.global.security.auth

import com.example.munjeongminbackend.domain.user.domain.repository.UserRepository
import com.example.munjeongminbackend.domain.user.exception.UserNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class AuthDetailsService (
        private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return AuthDetails(
                userRepository.findUserByEmail(username) ?: throw UserNotFoundException.EXCEPTION
        )
    }
}