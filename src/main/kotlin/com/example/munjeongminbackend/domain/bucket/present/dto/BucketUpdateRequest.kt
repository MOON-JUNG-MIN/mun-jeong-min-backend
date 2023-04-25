package com.example.munjeongminbackend.domain.bucket.present.dto

import java.time.LocalDateTime
import java.util.*

data class BucketUpdateRequest(
        val title: String,
        val content: String,
        val image: String,
        val targetDate: LocalDateTime
)