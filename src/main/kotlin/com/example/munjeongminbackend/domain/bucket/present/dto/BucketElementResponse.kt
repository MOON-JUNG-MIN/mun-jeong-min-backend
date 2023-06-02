package com.example.munjeongminbackend.domain.bucket.present.dto

data class BucketElementResponse(
        val id: Long,
        val title: String,
        val content: String,
        val image: String?,
        val targetDate: String,
        val roomId: Long,
        val roomName: String
)
