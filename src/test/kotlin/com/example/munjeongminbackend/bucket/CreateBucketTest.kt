package com.example.munjeongminbackend.bucket

import com.example.munjeongminbackend.domain.bucket.domain.Bucket
import com.example.munjeongminbackend.domain.bucket.domain.repository.BucketRepository
import com.example.munjeongminbackend.domain.bucket.present.dto.BucketCreateRequest
import com.example.munjeongminbackend.domain.bucket.service.CreateBucketService
import com.example.munjeongminbackend.domain.chat.domain.Room
import com.example.munjeongminbackend.domain.chat.domain.repository.RoomRepository
import com.example.munjeongminbackend.domain.member.domain.Member
import com.example.munjeongminbackend.domain.member.domain.repository.MemberRepository
import com.example.munjeongminbackend.domain.user.domain.User
import com.example.munjeongminbackend.domain.user.facade.UserFacade
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class CreateBucketTest {
    @Mock
    private lateinit var bucketRepository: BucketRepository

    @Mock
    private lateinit var memberRepository: MemberRepository

    @Mock
    private lateinit var roomRepository: RoomRepository

    @Mock
    private lateinit var userFacade: UserFacade

    @InjectMocks
    private lateinit var createBucketService: CreateBucketService

    @Test
    fun 버킷생성_성공() {
        val title = "title"
        val content = "content"
        val image = "image"
        val date = LocalDateTime.now()
        val end = false

        val user = User(
                "moondeve41@gmail.com",
                "판다d",
                "image",
                "device_token"
        )

        `when`(userFacade.getCurrentUser())
                .thenReturn(user)

        assertThat(user)
                .isNotNull()

        val bucket = Bucket(
                title,
                content,
                image,
                date,
                end,
                user
        )
        given(bucketRepository.save(any()))
                .willReturn(bucket)

        val member = Member(
                user,
                bucket
        )

        given(memberRepository.save(any()))
                .willReturn(member)

        val room = Room(
                title,
                user
        )

        given(roomRepository.save(any()))
                .willReturn(room)

        val bucketCreateRequest = BucketCreateRequest(
                title,
                content,
                image,
                date
        )

        createBucketService.execute(request = bucketCreateRequest)
    }
}