package com.example.munjeongminbackend.domain.image.present

import com.example.munjeongminbackend.domain.image.present.dto.ImageResponse
import com.example.munjeongminbackend.domain.image.service.ImageUploadService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/image")
class ImageController (
        private val imageUploadService: ImageUploadService
) {

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    fun upload(@RequestPart files: List<MultipartFile>): ImageResponse {
        return imageUploadService.execute(files)
    }
}