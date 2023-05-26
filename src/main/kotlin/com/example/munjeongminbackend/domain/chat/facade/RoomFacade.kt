package com.example.munjeongminbackend.domain.chat.facade

import com.example.munjeongminbackend.domain.bucket.domain.Bucket
import com.example.munjeongminbackend.domain.chat.domain.Room
import com.example.munjeongminbackend.domain.chat.domain.repository.RoomRepository
import com.example.munjeongminbackend.domain.chat.exception.RoomNotFoundException
import org.springframework.stereotype.Component

@Component
class RoomFacade (
        private val roomRepository: RoomRepository
) {

    fun getRoomByBucket(bucket: Bucket): Room {
        return roomRepository.findRoomByBucket(bucket) ?: throw RoomNotFoundException.EXCEPTION
    }
}