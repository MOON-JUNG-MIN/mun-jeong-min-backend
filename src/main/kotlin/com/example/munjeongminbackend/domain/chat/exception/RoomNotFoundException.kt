package com.example.munjeongminbackend.domain.chat.exception

import com.example.munjeongminbackend.global.error.custom.CustomException
import com.example.munjeongminbackend.global.error.custom.ErrorCode

object RoomNotFoundException : CustomException(ErrorCode.ROOM_NOT_FOUND) {
    val EXCEPTION = RoomNotFoundException
}