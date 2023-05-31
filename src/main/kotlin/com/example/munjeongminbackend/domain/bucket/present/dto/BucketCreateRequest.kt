package com.example.munjeongminbackend.domain.bucket.present.dto

import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

data class BucketCreateRequest (

        @NotBlank(message = "title 비어있어요")
        val title: String,

        @NotBlank(message = "content 비어있음")
        val content: String,

        val image: String?,

        @NotBlank(message = "날짜 요소 비어있음")
        val targetDate: String
)