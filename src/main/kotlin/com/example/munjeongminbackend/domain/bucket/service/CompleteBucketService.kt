package com.example.munjeongminbackend.domain.bucket.service

import com.example.munjeongminbackend.domain.bucket.domain.repository.BucketRepository
import com.example.munjeongminbackend.domain.bucket.exception.BucketNotFoundException
import com.example.munjeongminbackend.domain.member.domain.repository.MemberRepository
import com.example.munjeongminbackend.domain.member.exception.MemberNotFoundException
import com.example.munjeongminbackend.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
class CompleteBucketService (
        private val bucketRepository: BucketRepository,
        private val userFacade: UserFacade,
        private val memberRepository: MemberRepository
) {

    @Transactional
    fun execute(id: Long) {
        val bucket = bucketRepository.findBucketById(id) ?: throw BucketNotFoundException.EXCEPTION
        val user = userFacade.getCurrentUser()

        val members = memberRepository.findMembersByBucket(bucket).stream().map { it.user }.collect(Collectors.toList())

        if(!members.contains(user)) {
            throw MemberNotFoundException.EXCEPTION
        }

        bucket.endBucket(true)
    }

}