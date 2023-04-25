package com.example.munjeongminbackend.domain.image.exception

import com.example.munjeongminbackend.global.error.custom.CustomException
import com.example.munjeongminbackend.global.error.custom.ErrorCode

object SaveImageFailedException : CustomException(ErrorCode.SAVE_IMAGE_FAILED_EXCEPTION) {
    val EXCEPTION = SaveImageFailedException
}