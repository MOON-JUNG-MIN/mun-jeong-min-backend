package com.example.munjeongminbackend.domain.bucket.exception

import com.example.munjeongminbackend.global.error.custom.CustomException
import com.example.munjeongminbackend.global.error.custom.ErrorCode

object BucketNotAuth : CustomException(ErrorCode.BUCKET_NOT_AUTH) {
    val EXCEPTION = BucketNotFoundException
}