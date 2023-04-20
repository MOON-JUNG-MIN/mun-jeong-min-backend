package com.example.munjeongminbackend.domain.member.facade

import com.example.munjeongminbackend.domain.bucket.domain.Bucket
import com.example.munjeongminbackend.domain.bucket.present.dto.MemberProfileResponse
import com.example.munjeongminbackend.domain.member.domain.repository.MemberRepository
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class MemberFacade (
        private val memberRepository: MemberRepository
) {

    fun findUsersByBucket(bucket: Bucket): List<MemberProfileResponse> {
        val users = memberRepository.findMembersByBucket(bucket).stream()
                .map {
                    MemberProfileResponse(
                            it.user.nickname,
                            it.user.profileImage
                    )
                }.collect(Collectors.toList())

        return users
    }
}