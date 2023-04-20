package com.example.munjeongminbackend.domain.member.domain

import com.example.munjeongminbackend.domain.bucket.domain.Bucket
import com.example.munjeongminbackend.domain.user.domain.User
import com.example.munjeongminbackend.global.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "tbl_member")
data class Member (

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        val user: User,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "bucket_id", nullable = false)
        val bucket: Bucket

) : BaseEntity()