package com.example.munjeongminbackend.infra.fcm

import com.google.firebase.messaging.ApnsConfig
import com.google.firebase.messaging.Aps
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification
import org.springframework.stereotype.Service

@Service
class FcmService : MemberAddDetails {
    override fun sendMessage(token: String, title: String, content: String) {
        val message = Message.builder()
                .setToken(token)
                .setNotification(
                        Notification.builder()
                                .setTitle(title)
                                .setBody(content)
                                .build()
                )
                .setApnsConfig(
                        ApnsConfig.builder()
                                .setAps(
                                        Aps.builder()
                                                .setSound("default")
                                                .build()
                                ).build()
                ).build()
        FirebaseMessaging.getInstance().sendAsync(message)
    }
}