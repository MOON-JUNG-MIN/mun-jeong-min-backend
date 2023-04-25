package com.example.munjeongminbackend.domain.bucket.present.dto

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class MyBucketResponse (
        val id: Long,
        val title: String,
        val content: String,
        val image: String,
        val targetDate: LocalDate,
        val isEnd: Boolean,
        val startDate: LocalDateTime,
        val members: List<MemberProfileResponse>
)