package com.example.munjeongminbackend.global.error.exception

import com.example.munjeongminbackend.global.error.custom.CustomException
import com.example.munjeongminbackend.global.error.custom.ErrorCode

object InValidJwtException : CustomException(ErrorCode.INVALID_JWT_EXCEPTION) {
    val EXCEPTION = InValidJwtException
}