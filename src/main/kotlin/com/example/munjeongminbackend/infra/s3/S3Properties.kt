package com.example.munjeongminbackend.infra.s3

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "cloud.aws.s3")
class S3Properties (
        val bucket: String
)