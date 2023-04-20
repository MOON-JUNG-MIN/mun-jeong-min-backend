package com.example.munjeongminbackend.domain.bucket.service

import com.example.munjeongminbackend.domain.bucket.domain.repository.BucketRepository
import com.example.munjeongminbackend.domain.bucket.exception.BucketNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CompleteBucketService (
        private val bucketRepository: BucketRepository
) {

    @Transactional
    fun execute(id: Long) {
        val bucket = bucketRepository.findBucketById(id) ?: throw BucketNotFoundException.EXCEPTION

        bucket.endBucket(true)
    }

}