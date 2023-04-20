package com.example.munjeongminbackend.global.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@ConfigurationPropertiesScan(basePackages = ["com.example.munjeongminbackend"])
@Configuration
class PropertiesConfig {
}