package com.example.munjeongminbackend.domain.user.service

import com.example.munjeongminbackend.domain.user.domain.User
import com.example.munjeongminbackend.domain.user.domain.repository.UserRepository
import com.example.munjeongminbackend.domain.user.present.dto.CodeRequest
import com.example.munjeongminbackend.domain.user.present.dto.TokenResponse
import com.example.munjeongminbackend.global.properties.ClientProperties
import com.example.munjeongminbackend.global.security.jwt.JwtProvider
import com.example.munjeongminbackend.infra.feign.client.AccessTokenClient
import com.example.munjeongminbackend.infra.feign.client.UserInfoClient
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LoginService (
        private val userRepository: UserRepository,
        private val accessTokenClient: AccessTokenClient,
        private val userInfoClient: UserInfoClient,
        private val clientProperties: ClientProperties,
        private val jwtProvider: JwtProvider
) {

    @Transactional
    fun login(codeRequest: CodeRequest): TokenResponse {

        val access = accessTokenClient.access(
                code = codeRequest.code,
                clientId = clientProperties.clientId,
                clientSecret = clientProperties.clientSecret,
                redirectUri = clientProperties.redirectUri,
                grantType = clientProperties.authorizationGrantType
        )

        val token = "Bearer ${access.accessToken}"

        val user = userInfoClient.info(
                token
        )

        userRepository.findUserByEmail(user.email)?.let {
            return TokenResponse(
                    jwtProvider.getToken(it.email).accessToken
            )
        }

        val one = userRepository.save(
                User(
                        nickname = user.name,
                        email = user.email,
                        profileImage = user.picture,
                        deviceToken = codeRequest.deviceToken
                )
        )

        return TokenResponse(
                jwtProvider.getToken(one.email).accessToken
        )

    }

}