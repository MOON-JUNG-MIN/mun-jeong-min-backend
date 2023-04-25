package com.example.munjeongminbackend.infra.s3

import org.springframework.web.multipart.MultipartFile

interface ImageService {
    fun upload(file: MultipartFile): String
}