package com.example.munjeongminbackend.domain.chat.domain

import com.example.munjeongminbackend.domain.user.domain.User
import com.example.munjeongminbackend.global.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "tbl_room")
data class Room (

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        val user: User

) : BaseEntity()