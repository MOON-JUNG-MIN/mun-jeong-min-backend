package com.example.munjeongminbackend.infra.s3

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.example.munjeongminbackend.domain.image.exception.ImageNotValueException
import com.example.munjeongminbackend.domain.image.exception.SaveImageFailedException
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Component
class S3Service (
        private val s3Properties: S3Properties,
        private val amazonS3Client: AmazonS3Client
):ImageService {
    override fun upload(file: MultipartFile): String {
        if (file.isEmpty) {
            throw ImageNotValueException.EXCEPTION
        }
        val fileName: String = (s3Properties.bucket + "/" + UUID.randomUUID()).toString() + file.originalFilename
        try {
            val putObjectRequest = PutObjectRequest(
                    s3Properties.bucket,
                    fileName,
                    file.inputStream,
                    getObjectMetadata(file)
            )
            amazonS3Client.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead))
        } catch (e: Exception) {
            e.printStackTrace()
            throw SaveImageFailedException.EXCEPTION
        }
        return getFileUrl(fileName)
    }

    private fun getObjectMetadata(file: MultipartFile): ObjectMetadata {
        val objectMetadata = ObjectMetadata()
        objectMetadata.contentLength = file.size
        objectMetadata.contentType = file.contentType
        return objectMetadata
    }

    fun getFileUrl(fileName: String): String {
        return amazonS3Client.getUrl(s3Properties.bucket, fileName).toString()
    }


}