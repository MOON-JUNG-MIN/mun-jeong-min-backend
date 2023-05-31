package com.example.munjeongminbackend.domain.bucket.service

import com.example.munjeongminbackend.domain.bucket.domain.Bucket
import com.example.munjeongminbackend.domain.bucket.domain.repository.BucketRepository
import com.example.munjeongminbackend.domain.bucket.exception.RequestNullException
import com.example.munjeongminbackend.domain.bucket.present.dto.BucketCreateRequest
import com.example.munjeongminbackend.domain.chat.domain.Room
import com.example.munjeongminbackend.domain.chat.domain.repository.RoomRepository
import com.example.munjeongminbackend.domain.member.domain.Member
import com.example.munjeongminbackend.domain.member.domain.repository.MemberRepository
import com.example.munjeongminbackend.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class CreateBucketService (
        private val bucketRepository: BucketRepository,
        private val memberRepository: MemberRepository,
        private val roomRepository: RoomRepository,
        private val userFacade: UserFacade
) {
    @Transactional
    fun execute(request: BucketCreateRequest) {
        val user = userFacade.getCurrentUser()

        if(request.targetDate == "" || request.targetDate == " ") {
            throw RequestNullException.EXCEPTION
        }

        val bucket = bucketRepository.save(
                Bucket(
                        title = request.title,
                        content = request.content,
                        request.image,
                        LocalDate.of(
                                request.targetDate.split("-")[0].toInt(),
                                request.targetDate.split("-")[1].toInt(),
                                request.targetDate.split("-")[2].toInt()
                        ),
                        isEnd = false,
                        user = user
                )
        )

        memberRepository.save(
                Member(
                        bucket = bucket,
                        user = user
                )
        )
        roomRepository.save(
                Room(
                        bucket,
                        user
                )
        )
    }

}