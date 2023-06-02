package com.example.munjeongminbackend.domain.bucket.service

import com.example.munjeongminbackend.domain.bucket.facade.BucketFacade
import com.example.munjeongminbackend.domain.bucket.present.dto.BucketElementResponse
import com.example.munjeongminbackend.domain.chat.facade.RoomFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.format.DateTimeFormatter

@Service
class BucketElementService (
        private val bucketFacade: BucketFacade,
        private val roomFacade: RoomFacade
) {

    @Transactional(readOnly = true)
    fun execute(id: Long): BucketElementResponse {
        val bucket = bucketFacade.findById(id)
        val room = roomFacade.getRoomByBucket(bucket)

        return BucketElementResponse(
                bucket.id,
                bucket.title,
                bucket.content,
                bucket.image,
                bucket.targetDate.format(DateTimeFormatter.ofPattern("YYYY.MM.dd")),
                room.id,
                room.bucket.title
        )
    }
}