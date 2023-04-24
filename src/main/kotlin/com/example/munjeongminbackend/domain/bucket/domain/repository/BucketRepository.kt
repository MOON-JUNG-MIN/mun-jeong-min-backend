package com.example.munjeongminbackend.domain.bucket.domain.repository

import com.example.munjeongminbackend.domain.bucket.domain.Bucket
import com.example.munjeongminbackend.domain.user.domain.User
import org.springframework.data.repository.CrudRepository

interface BucketRepository : CrudRepository<Bucket, Long> {
    fun findBucketById(id: Long): Bucket?

    fun countAllByUser(user: User): Int
}