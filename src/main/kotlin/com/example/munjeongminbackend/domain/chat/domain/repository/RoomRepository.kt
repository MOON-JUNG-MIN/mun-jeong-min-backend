package com.example.munjeongminbackend.domain.chat.domain.repository

import com.example.munjeongminbackend.domain.chat.domain.Room
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RoomRepository : CrudRepository<Room, Long> {
}