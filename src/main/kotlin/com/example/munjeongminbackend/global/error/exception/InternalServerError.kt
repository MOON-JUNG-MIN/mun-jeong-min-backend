package com.example.munjeongminbackend.global.error.exception

import com.example.munjeongminbackend.global.error.custom.CustomException
import com.example.munjeongminbackend.global.error.custom.ErrorCode

object InternalServerException : CustomException(ErrorCode.INTERNAL_SERVER_ERROR) {
    val EXCEPTION = InternalServerException
}