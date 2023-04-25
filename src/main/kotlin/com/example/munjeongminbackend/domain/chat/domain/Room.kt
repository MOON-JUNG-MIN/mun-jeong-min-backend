package com.example.munjeongminbackend.domain.chat.domain

import com.example.munjeongminbackend.domain.bucket.domain.Bucket
import com.example.munjeongminbackend.domain.user.domain.User
import com.example.munjeongminbackend.global.entity.BaseEntity
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tbl_room")
data class Room (

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "bucket_id", nullable = false)
        val bucket: Bucket,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        val user: User

) : BaseEntity()