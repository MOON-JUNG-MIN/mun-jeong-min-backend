package com.example.munjeongminbackend.domain.user.exception

import com.example.munjeongminbackend.global.error.custom.CustomException
import com.example.munjeongminbackend.global.error.custom.ErrorCode

object UserNotFoundException : CustomException(ErrorCode.USER_NOT_FOUND) {
    val EXCEPTION = UserNotFoundException
}