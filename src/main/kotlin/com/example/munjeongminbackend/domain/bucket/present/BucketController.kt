package com.example.munjeongminbackend.domain.bucket.present

import com.example.munjeongminbackend.domain.bucket.present.dto.BucketCreateRequest
import com.example.munjeongminbackend.domain.bucket.present.dto.BucketElementResponse
import com.example.munjeongminbackend.domain.bucket.present.dto.BucketUpdateRequest
import com.example.munjeongminbackend.domain.bucket.present.dto.MyBucketListResponse
import com.example.munjeongminbackend.domain.bucket.service.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bucket")
class BucketController (
        private val createBucketService: CreateBucketService,
        private val updateBucketService: UpdateBucketService,
        private val deleteBucketService: DeleteBucketService,
        private val completeBucketService: CompleteBucketService,
        private val myBucketService: MyBucketService,
        private val bucketElementService: BucketElementService
) {

    @GetMapping("/{id}")
    fun getOne(@PathVariable("id") id: Long): BucketElementResponse {
        return bucketElementService.execute(id)
    }

    @GetMapping
    fun read(): MyBucketListResponse {
        return myBucketService.execute()
    }

    @PostMapping
    fun create(@RequestBody request: BucketCreateRequest) {
        createBucketService.execute(request)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody request: BucketUpdateRequest) {
        updateBucketService.execute(id, request)
    }
    
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        deleteBucketService.execute(id)
    }

    @PutMapping("/end/{id}")
    fun end(@PathVariable("id") id: Long) {
        completeBucketService.execute(id)
    }

}