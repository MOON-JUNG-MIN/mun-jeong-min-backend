package com.example.munjeongminbackend.domain.image.service

import com.example.munjeongminbackend.domain.image.present.dto.ImageResponse
import com.example.munjeongminbackend.infra.s3.ImageService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.util.stream.Collectors

@Service
class ImageUploadService (
        private val imageService: ImageService
) {

    @Transactional
    fun execute(files: List<MultipartFile>): ImageResponse {
        val list = files.stream()
                .map {
                    imageService.upload(it)
                }.collect(Collectors.toList())

        return ImageResponse(list)
    }
}