package com.example.munjeongminbackend.domain.member.exception

import com.example.munjeongminbackend.global.error.custom.CustomException
import com.example.munjeongminbackend.global.error.custom.ErrorCode

object MemberExistException : CustomException(ErrorCode.MEMBER_EXIST) {
    val EXCEPTION = MemberExistException
}