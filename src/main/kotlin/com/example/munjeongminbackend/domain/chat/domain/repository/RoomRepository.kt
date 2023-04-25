package com.example.munjeongminbackend.domain.chat.domain.repository

import com.example.munjeongminbackend.domain.bucket.domain.Bucket
import com.example.munjeongminbackend.domain.chat.domain.Room
import com.example.munjeongminbackend.domain.user.domain.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RoomRepository : CrudRepository<Room, Long> {
    fun findAllByUser(user: User): List<Room>

    fun findRoomById(id: Long): Room?

    fun findRoomByBucket(bucket: Bucket): Room?
}