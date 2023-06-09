package com.example.munjeongminbackend.domain.member.service

import com.example.munjeongminbackend.domain.bucket.domain.repository.BucketRepository
import com.example.munjeongminbackend.domain.bucket.exception.BucketNotFoundException
import com.example.munjeongminbackend.domain.chat.domain.Room
import com.example.munjeongminbackend.domain.chat.domain.repository.RoomRepository
import com.example.munjeongminbackend.domain.member.domain.Member
import com.example.munjeongminbackend.domain.member.domain.repository.MemberRepository
import com.example.munjeongminbackend.domain.member.exception.MemberExistException
import com.example.munjeongminbackend.domain.member.present.dto.MemberAddRequest
import com.example.munjeongminbackend.domain.user.domain.repository.UserRepository
import com.example.munjeongminbackend.domain.user.exception.UserNotFoundException
import com.example.munjeongminbackend.domain.user.facade.UserFacade
import com.example.munjeongminbackend.infra.fcm.FcmService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberAddService (
        private val memberRepository: MemberRepository,
        private val userRepository: UserRepository,
        private val bucketRepository: BucketRepository,
        private val fcmService: FcmService,
        private val userFacade: UserFacade,
        private val roomRepository: RoomRepository
) {

    @Transactional
    fun execute(id: Long, request: MemberAddRequest) {
        val user = userFacade.getCurrentUser()
        val bucket = bucketRepository.findBucketById(id) ?: throw BucketNotFoundException.EXCEPTION
        val member = userRepository.findUserByEmail(request.email) ?: throw UserNotFoundException.EXCEPTION
        
        memberRepository.findMemberByUser(member)?.let {
            throw MemberExistException.EXCEPTION
        }
        
        memberRepository.save(
                Member(
                        bucket = bucket,
                        user = member
                )
        )
        fcmService.sendMessage(member.deviceToken, "버킷리스트에 초대되었습니다.", "${user.nickname}님의 ${bucket.title}에 초대 되었습니다")

    }

}