package com.example.munjeongminbackend.domain.bucket.service

import com.example.munjeongminbackend.domain.bucket.domain.repository.BucketRepository
import com.example.munjeongminbackend.domain.bucket.exception.BucketNotAuth
import com.example.munjeongminbackend.domain.bucket.exception.BucketNotFoundException
import com.example.munjeongminbackend.domain.bucket.facade.BucketFacade
import com.example.munjeongminbackend.domain.chat.domain.repository.MessageRepository
import com.example.munjeongminbackend.domain.chat.domain.repository.RoomRepository
import com.example.munjeongminbackend.domain.chat.exception.RoomNotFoundException
import com.example.munjeongminbackend.domain.member.domain.repository.MemberRepository
import com.example.munjeongminbackend.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
class DeleteBucketService (
        private val bucketFacade: BucketFacade,
        private val bucketRepository: BucketRepository,
        private val memberRepository: MemberRepository,
        private val userFacade: UserFacade,
        private val roomRepository: RoomRepository,
        private val messageRepository: MessageRepository
) {

    @Transactional
    fun execute(id: Long) {
        val bucket = bucketFacade.findById(id)
        val user = userFacade.getCurrentUser()

        if(bucket.user != user) {
            throw BucketNotAuth.EXCEPTION
        }

        val members = memberRepository.findMembersByBucket(bucket).stream().map { it }.collect(Collectors.toList())
        memberRepository.deleteAll(members)

        val room = roomRepository.findRoomByBucket(bucket) ?: throw RoomNotFoundException.EXCEPTION

        messageRepository.deleteAllByRoom(room)
        roomRepository.delete(room)

        bucketRepository.delete(bucket)
    }

}