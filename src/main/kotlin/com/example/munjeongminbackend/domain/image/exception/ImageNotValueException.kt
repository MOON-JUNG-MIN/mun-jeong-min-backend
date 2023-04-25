package com.example.munjeongminbackend.domain.image.exception

import com.example.munjeongminbackend.global.error.custom.CustomException
import com.example.munjeongminbackend.global.error.custom.ErrorCode

object ImageNotValueException : CustomException(ErrorCode.IMAGE_NOT_VALUE_EXCEPTION) {
    val EXCEPTION = ImageNotValueException
}