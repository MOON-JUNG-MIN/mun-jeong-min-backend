package com.example.munjeongminbackend.domain.bucket.exception

import com.example.munjeongminbackend.global.error.custom.CustomException
import com.example.munjeongminbackend.global.error.custom.ErrorCode

object BucketNotFoundException : CustomException(ErrorCode.BUCKET_NOT_FOUND) {
    val EXCEPTION = BucketNotFoundException
}