package com.example.munjeongminbackend.domain.chat.exception

import com.example.munjeongminbackend.global.error.custom.CustomException
import com.example.munjeongminbackend.global.error.custom.ErrorCode

object ChatTokenNullException : CustomException(ErrorCode.CHAT_TOKEN_NULL) {
    val EXCEPTION = ChatTokenNullException
}