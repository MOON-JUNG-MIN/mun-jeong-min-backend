package com.example.munjeongminbackend.domain.member.exception

import com.example.munjeongminbackend.global.error.custom.CustomException
import com.example.munjeongminbackend.global.error.custom.ErrorCode

object MemberNotFoundException : CustomException(ErrorCode.MEMBER_NOT_FOUND) {
    val EXCEPTION = MemberNotFoundException
}