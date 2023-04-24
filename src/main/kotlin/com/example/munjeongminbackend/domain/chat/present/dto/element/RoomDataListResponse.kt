package com.example.munjeongminbackend.domain.chat.present.dto.element

import java.time.LocalDateTime

data class RoomDataListResponse(
        val messageId: Long,
        val username: String,
        val profileImage: String,
        val message: String,
        val date: LocalDateTime
)
