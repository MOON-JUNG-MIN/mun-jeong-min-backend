package com.example.munjeongminbackend.domain.chat.domain.repository

import com.example.munjeongminbackend.domain.chat.domain.Message
import com.example.munjeongminbackend.domain.chat.domain.Room
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : CrudRepository<Message, Long> {
    fun findAllByRoom(room: Room): List<Message>
}