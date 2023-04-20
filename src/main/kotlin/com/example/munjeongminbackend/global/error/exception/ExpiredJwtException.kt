package com.example.munjeongminbackend.global.error.exception

import com.example.munjeongminbackend.global.error.custom.CustomException
import com.example.munjeongminbackend.global.error.custom.ErrorCode

object ExpiredJwtException : CustomException(ErrorCode.EXPIRED_JWT_EXCEPTION) {
    val EXCEPTION = ExpiredJwtException
}