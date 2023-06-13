package com.example.munjeongminbackend.domain.chat.service

import com.example.munjeongminbackend.domain.chat.domain.Message
import com.example.munjeongminbackend.domain.chat.domain.Room
import com.example.munjeongminbackend.domain.chat.domain.repository.MessageRepository
import com.example.munjeongminbackend.domain.chat.domain.repository.RoomRepository
import com.example.munjeongminbackend.domain.chat.exception.RoomNotFoundException
import com.example.munjeongminbackend.domain.chat.facade.RoomFacade
import com.example.munjeongminbackend.domain.chat.present.dto.MessageRealRequest
import com.example.munjeongminbackend.domain.chat.present.dto.element.RoomDataListResponse
import com.example.munjeongminbackend.domain.chat.present.dto.element.RoomDataResponse
import com.example.munjeongminbackend.domain.chat.present.dto.list.RoomListResponse
import com.example.munjeongminbackend.domain.chat.present.dto.list.RoomResponse
import com.example.munjeongminbackend.domain.user.domain.repository.UserRepository
import com.example.munjeongminbackend.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
class RoomService (
        private val roomRepository: RoomRepository,
        private val userFacade: UserFacade,
        private val messageRepository: MessageRepository
) {

    @Transactional(readOnly = true)
    fun allRoom(): RoomListResponse {
        val rooms = roomRepository.findAllByUser(userFacade.getCurrentUser()).stream()
                .map {
                    RoomResponse(
                            it.id,
                            it.bucket.title
                    )
                }.collect(Collectors.toList())

        return RoomListResponse(
                rooms
        )
    }

    @Transactional(readOnly = true)
    fun oneRoom(id: Long): RoomDataResponse {
        val room = roomRepository.findRoomById(id) ?: throw RoomNotFoundException.EXCEPTION

        val data = messageRepository.findAllByRoom(room).stream()
                .map {
                    RoomDataListResponse(
                            it.id,
                            it.user.nickname,
                            it.user.profileImage,
                            it.content,
                            it.createdAt
                    )
                }.collect(Collectors.toList())

        return RoomDataResponse (
                room.id,
                room.bucket.title,
                data
        )
    }

    @Transactional
    fun send(request: MessageRealRequest, id: Long) {
        val user = userFacade.getCurrentUser()
        val room: Room = roomRepository.findRoomById(id) ?: throw RoomNotFoundException.EXCEPTION

        messageRepository.save(
                Message(
                        request.message, user, room
                )
        )
    }
}
