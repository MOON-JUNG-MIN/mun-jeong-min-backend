package com.example.munjeongminbackend.domain.chat.domain

import com.example.munjeongminbackend.domain.user.domain.User
import com.example.munjeongminbackend.global.entity.BaseEntity
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tbl_room")
data class Room (

        @field:NotNull
        @field:Column(columnDefinition = "VARCHAR(100)")
        val roomName: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        val user: User

) : BaseEntity()