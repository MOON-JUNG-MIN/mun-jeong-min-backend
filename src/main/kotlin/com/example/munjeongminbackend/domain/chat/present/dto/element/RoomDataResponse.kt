package com.example.munjeongminbackend.domain.chat.present.dto.element

data class RoomDataResponse(
        val roomId: Long,
        val roomName: String,
        val data: List<RoomDataListResponse>
)