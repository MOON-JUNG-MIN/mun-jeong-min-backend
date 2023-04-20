package com.example.munjeongminbackend.domain.bucket.domain

import com.example.munjeongminbackend.domain.user.domain.User
import com.example.munjeongminbackend.global.entity.BaseEntity
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tbl_bucket")
data class Bucket(

        @field:NotNull
        @field:Column(columnDefinition = "VARCHAR(100)")
        var title: String,

        @field:NotNull
        @field:Column(columnDefinition = "VARCHAR(500)")
        var content: String,

        @field:NotNull
        @field:Column(columnDefinition = "VARCHAR(200)")
        var image: String,

        @field:NotNull
        var targetDate: Date,

        @field:NotNull
        var isEnd: Boolean,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        val user: User

) : BaseEntity() {
    fun update(title: String, content: String, image: String, targetDate: Date) {
        this.title = title
        this.content = content
        this.image = image
        this.targetDate = targetDate
    }

    fun endBucket(isEnd: Boolean) {
        this.isEnd = isEnd
    }
}