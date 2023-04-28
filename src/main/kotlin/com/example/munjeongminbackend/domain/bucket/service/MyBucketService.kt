package com.example.munjeongminbackend.domain.bucket.service

import com.example.munjeongminbackend.domain.bucket.domain.repository.BucketRepository
import com.example.munjeongminbackend.domain.bucket.present.dto.MyBucketListResponse
import com.example.munjeongminbackend.domain.bucket.present.dto.MyBucketResponse
import com.example.munjeongminbackend.domain.member.domain.repository.MemberRepository
import com.example.munjeongminbackend.domain.member.facade.MemberFacade
import com.example.munjeongminbackend.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
class MyBucketService (
        private val memberRepository: MemberRepository,
        private val userFacade: UserFacade,
        private val memberFacade: MemberFacade,
        private val bucketRepository: BucketRepository
) {

    @Transactional(readOnly = true)
    fun execute(): MyBucketListResponse {
        val user = userFacade.getCurrentUser()
        var check = 0
        var tmp = 0

        val buckets = memberRepository.findMembersByUser(user)
                .stream()
                .map {
                    if (it.bucket.isEnd) {
                        check += 1
                    }
                    tmp += 1
                    val cnt = it.bucket
                    MyBucketResponse (
                            id = cnt.id,
                            title = cnt.title,
                            content = cnt.content,
                            image = cnt.image,
                            targetDate = cnt.targetDate,
                            isEnd = cnt.isEnd,
                            startDate = cnt.createdAt,
                            members = memberFacade.findUsersByBucket(cnt)
                    )
                }.collect(Collectors.toList())

        var percent = check / tmp.toFloat() * 100

        if(check == 0) {
            percent = 0F
        }

        return MyBucketListResponse(
                buckets,
                percent
        )
    }
}