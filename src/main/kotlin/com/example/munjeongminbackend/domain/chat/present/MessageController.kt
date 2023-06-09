package com.example.munjeongminbackend.domain.chat.present

import com.example.munjeongminbackend.domain.chat.domain.Message
import com.example.munjeongminbackend.domain.chat.domain.repository.MessageRepository
import com.example.munjeongminbackend.domain.chat.domain.repository.RoomRepository
import com.example.munjeongminbackend.domain.chat.exception.ChatTokenNullException
import com.example.munjeongminbackend.domain.chat.exception.RoomNotFoundException
import com.example.munjeongminbackend.domain.chat.present.dto.MessageRequest
import com.example.munjeongminbackend.domain.chat.present.dto.list.MessageResponse
import com.example.munjeongminbackend.domain.user.domain.repository.UserRepository
import com.example.munjeongminbackend.domain.user.exception.UserNotFoundException
import com.example.munjeongminbackend.global.security.jwt.JwtProvider
import lombok.extern.slf4j.Slf4j
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
class MessageController (
        private val simpMessageSendingOperations: SimpMessageSendingOperations,
        private val messageRepository: MessageRepository,
        private val roomRepository: RoomRepository,
        private val jwtProvider: JwtProvider,
        private val userRepository: UserRepository
) {

    @MessageMapping("/chat/message/{roomId}")
    @SendTo("/topic/chat/room/{roomId}")
    fun enter(@Payload message: MessageRequest, @DestinationVariable roomId: Long, stompHeaderAccessor: StompHeaderAccessor): MessageResponse {
        val token = stompHeaderAccessor.getFirstNativeHeader("Authorization")
        val email: String = jwtProvider.parseToken(token)
                ?: throw ChatTokenNullException.EXCEPTION

        val subject = jwtProvider.getTokenBody(email)
        val user = userRepository.findUserByEmail(subject) ?: throw UserNotFoundException.EXCEPTION

        val room = roomRepository.findRoomById(roomId) ?: throw RoomNotFoundException.EXCEPTION

        messageRepository.save(
                Message(message.message, user, room)
        )

        //simpMessageSendingOperations.convertAndSend("/topic/chat/room/${message.roomId}", messageResponse)
        return MessageResponse(
                sender = user.nickname,
                senderImage = user.profileImage,
                message.message
        )
    }
}
// 채팅방에 입장할 때 이전 메세지들을 api로 다 불러옴
// 후에 소켓 연결
// 후에 /topic/chat/room/{roomId}를 클라이언트에서 구독함
// /app/chat/message에 content, room_id를 json값으로 담아서 메세지 전송
// 메세지를 /topic/char/room/{roomId}에 보내고 db 저장