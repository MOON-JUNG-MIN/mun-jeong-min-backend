package com.example.munjeongminbackend.domain.chat.present.dto.list

data class MessageResponse (
        val sender: String,
        val senderImage: String,
        val message: String
)