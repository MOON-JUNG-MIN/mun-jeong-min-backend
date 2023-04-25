package com.example.munjeongminbackend.global.error.custom

import org.springframework.http.HttpStatus

enum class ErrorCode (
        private val status: HttpStatus,
        private val message: String
) : ErrorProperty {

    BUCKET_NOT_AUTH(HttpStatus.BAD_REQUEST, "권한이 없음"),
    SAVE_IMAGE_FAILED_EXCEPTION(HttpStatus.BAD_REQUEST, "이미지 저장 실패"),

    IMAGE_NOT_VALUE_EXCEPTION(HttpStatus.NOT_FOUND, "이미지를 찾지 못함"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찿지 못함"),
    BUCKET_NOT_FOUND(HttpStatus.NOT_FOUND, "버킷을 찾지 못함"),

    MEMBER_EXIST(HttpStatus.CONFLICT, "멤버가 이미 존재함"),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "멤버를 찾지 못함"),
    ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "채팅방을 찾지 못함"),

    INVALID_JWT_EXCEPTION(HttpStatus.UNAUTHORIZED, "토큰 인증 오류"),
    EXPIRED_JWT_EXCEPTION(HttpStatus.UNAUTHORIZED, "토큰 만료"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");

    override fun status() = status.value()
    override fun message() = message
}