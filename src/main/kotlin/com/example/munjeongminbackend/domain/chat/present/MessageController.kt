package com.example.munjeongminbackend.domain.chat.present

import com.example.munjeongminbackend.domain.chat.domain.type.MessageType
import com.example.munjeongminbackend.domain.chat.present.dto.MessageRequest
import com.example.munjeongminbackend.infra.fcm.FcmService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController (
        private val simpMessageSendingOperations: SimpMessageSendingOperations
) {

    @MessageMapping("/chat/message")
    fun enter(messageRequest: MessageRequest) {
        var message = messageRequest.message
        if (MessageType.ENTER.equals(messageRequest.messageType)) {
            message = "새로운 입장"
        }
        simpMessageSendingOperations.convertAndSend("/topic/chat/room/"+messageRequest.roomId, message)
    }
}