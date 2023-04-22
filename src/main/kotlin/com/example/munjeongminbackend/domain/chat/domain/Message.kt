package com.example.munjeongminbackend.domain.chat.domain

import com.example.munjeongminbackend.domain.chat.domain.type.MessageType
import com.example.munjeongminbackend.domain.user.domain.User
import com.example.munjeongminbackend.global.entity.BaseEntity
import com.sun.istack.NotNull
import javax.persistence.*

@Entity
@Table(name = "tbl_chat")
data class Message (

        @field:NotNull
        @field:Column(columnDefinition = "VARCHAR(500)")
        val content: String,

        @field:NotNull
        @field:Enumerated(EnumType.STRING)
        val messageType: MessageType,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        val user: User,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "room_id", nullable = false)
        val room: Room

) : BaseEntity()