package com.example.munjeongminbackend.domain.chat.present.dto

import com.example.munjeongminbackend.domain.chat.domain.type.MessageType

data class MessageRequest(
        val message: String,
        val roomId: Long,
        val messageType: MessageType
)
