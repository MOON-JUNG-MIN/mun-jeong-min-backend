package com.example.munjeongminbackend.domain.bucket.service

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
        private val memberFacade: MemberFacade
) {

    @Transactional(readOnly = true)
    fun execute(): MyBucketListResponse {
        val user = userFacade.getCurrentUser()

        val buckets = memberRepository.findMembersByUser(user)
                .stream()
                .map {
                    val cnt = it.bucket
                    MyBucketResponse (
                            id = it.bucket.id,
                            title = cnt.title,
                            content = cnt.content,
                            image = cnt.image,
                            targetDate = cnt.targetDate,
                            isEnd = cnt.isEnd,
                            startDate = cnt.createdAt,
                            members = memberFacade.findUsersByBucket(cnt)
                    )
                }.collect(Collectors.toList())

        return MyBucketListResponse(
                buckets
        )
    }
}