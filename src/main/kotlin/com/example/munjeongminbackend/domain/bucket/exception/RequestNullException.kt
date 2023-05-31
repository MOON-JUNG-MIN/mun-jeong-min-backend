package com.example.munjeongminbackend.domain.bucket.exception

import com.example.munjeongminbackend.global.error.custom.CustomException
import com.example.munjeongminbackend.global.error.custom.ErrorCode

object RequestNullException : CustomException(ErrorCode.REQUEST_NULL) {
    val EXCEPTION = RequestNullException
}