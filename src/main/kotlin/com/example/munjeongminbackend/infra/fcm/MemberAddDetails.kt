package com.example.munjeongminbackend.infra.fcm

interface MemberAddDetails {
    fun sendMessage(token: String, title: String, content: String)
}