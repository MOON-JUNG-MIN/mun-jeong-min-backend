package com.example.munjeongminbackend.domain.bucket.present.dto

import java.util.*

data class BucketCreateRequest (
        val title: String,
        val content: String,
        val image: String,
        val targetDate: Date
)