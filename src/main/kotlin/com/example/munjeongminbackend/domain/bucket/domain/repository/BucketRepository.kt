package com.example.munjeongminbackend.domain.bucket.domain.repository

import com.example.munjeongminbackend.domain.bucket.domain.Bucket
import org.springframework.data.repository.CrudRepository

interface BucketRepository : CrudRepository<Bucket, Long> {
    fun findBucketById(id: Long): Bucket?
}