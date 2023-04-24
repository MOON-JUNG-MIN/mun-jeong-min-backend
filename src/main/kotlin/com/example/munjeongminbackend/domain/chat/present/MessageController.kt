package com.example.munjeongminbackend.domain.chat.present

import com.example.munjeongminbackend.domain.chat.domain.Message
import com.example.munjeongminbackend.domain.chat.domain.repository.MessageRepository
import com.example.munjeongminbackend.domain.chat.domain.repository.RoomRepository
import com.example.munjeongminbackend.domain.chat.exception.RoomNotFoundException
import com.example.munjeongminbackend.domain.chat.present.dto.MessageRequest
import com.example.munjeongminbackend.domain.user.facade.UserFacade
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController (
        private val simpMessageSendingOperations: SimpMessageSendingOperations,
        private val messageRepository: MessageRepository,
        private val roomRepository: RoomRepository,
        private val userFacade: UserFacade
) {

    @MessageMapping("/chat/message")
    fun enter(@Payload messageRequest: MessageRequest) {
        val room = roomRepository.findRoomById(messageRequest.roomId) ?: throw RoomNotFoundException.EXCEPTION
        val user = userFacade.getCurrentUser()
        val message = messageRequest.message

        simpMessageSendingOperations.convertAndSend("/topic/chat/room/"+messageRequest.roomId, message)
        messageRepository.save(
                Message(message, user, room)
        )
    }
}
// 채팅방에 입장할 때 이전 메세지들을 api로 다 불러옴
// 후에 소켓 연결
// 후에 /topic/chat/room/{roomId}를 클라이언트에서 구독함
// 메세지를 /topic/char/room/{roomId}에 보내고 db 저장