package com.example.munjeongminbackend.domain.user.domain.repository

import com.example.munjeongminbackend.domain.user.domain.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findUserByEmail(email: String): User?
}