package com.example.munjeongminbackend.domain.user.domain

import com.example.munjeongminbackend.global.entity.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tbl_user")
data class User (

        @field:NotNull
        @field:Column(columnDefinition = "VARCHAR(100)")
        val email: String,

        @field:NotNull
        @field:Column(columnDefinition = "VARCHAR(100)")
        val nickname: String,

        @field:NotNull
        val profileImage: String

) : BaseEntity()