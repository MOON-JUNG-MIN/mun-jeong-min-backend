package com.example.munjeongminbackend.domain.chat.present.dto

data class MessageRequest(
        val message: String,
        val roomId: Long
)
