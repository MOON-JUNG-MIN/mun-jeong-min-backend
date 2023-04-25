package com.example.munjeongminbackend.user

import com.example.munjeongminbackend.domain.user.domain.User
import com.example.munjeongminbackend.domain.user.domain.repository.UserRepository
import com.example.munjeongminbackend.domain.user.present.dto.CodeRequest
import com.example.munjeongminbackend.domain.user.present.dto.TokenResponse
import com.example.munjeongminbackend.domain.user.service.LoginService
import com.example.munjeongminbackend.global.properties.ClientProperties
import com.example.munjeongminbackend.global.security.jwt.JwtProvider
import com.example.munjeongminbackend.infra.feign.client.AccessTokenClient
import com.example.munjeongminbackend.infra.feign.client.UserInfoClient
import com.example.munjeongminbackend.infra.feign.dto.UserAccessDto
import com.example.munjeongminbackend.infra.feign.dto.UserInfoDto
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class LoginTest {
    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var  accessTokenClient: AccessTokenClient

    @Mock
    private lateinit var  userInfoClient: UserInfoClient

    @Mock
    private lateinit var  clientProperties: ClientProperties

    @Mock
    private lateinit var jwtProvider: JwtProvider

    @InjectMocks
    private lateinit var loginService: LoginService

    @Test
    fun 로그인_성공() {
        val code = "4l1k2mklqirpnkl"
        val userAccessDto = UserAccessDto(
                "eyJ0eXBlIjoiYWNjZXNzIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJtb29uZGV2ZTQxQGdtYWlsLmNvbSIsImlhdCI6MTY4MjM4MDIzOSwiZXhwIjoxNzAwMzgwMjM5fQ.pxVkCL3bWy_5TN0e2P-iG2zmVXiXAnFOqYv3pohJpLU"
        )

        `when`(accessTokenClient.access(
                code,
                clientProperties.clientId,
                clientProperties.clientSecret,
                clientProperties.redirectUri,
                clientProperties.authorizationGrantType)
        ).thenReturn(userAccessDto)

        val token = "Bearer eyJ0eXBlIjoiYWNjZXNzIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJtb29uZGV2ZTQxQGdtYWlsLmNvbSIsImlhdCI6MTY4MjM4MDIzOSwiZXhwIjoxNzAwMzgwMjM5fQ.pxVkCL3bWy_5TN0e2P-iG2zmVXiXAnFOqYv3pohJpLU"
        val userInfoDto = UserInfoDto(
                "moondeve41@gmail.com",
                "판다d",
                "https://lh3.googleusercontent.com/a/AGNmyxY3n3AwTSBjAEZ1-4h_s2WVh1TqI440Z_or_9S3=s96-c"
        )

        `when`(userInfoClient.info(
                token
        )).thenReturn(userInfoDto)

        val user = User(
                "moondeve41@gmail.com",
                "판다d",
                "https://lh3.googleusercontent.com/a/AGNmyxY3n3AwTSBjAEZ1-4h_s2WVh1TqI440Z_or_9S3=s96-c",
                "device_token"
        )

        given(userRepository.save(any()))
                .willReturn(user)

        val tokenResponse = TokenResponse(
                "Bearer eyJ0eXBlIjoiYWNjZXNzIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJtb29uZGV2ZTQxQGdtYWlsLmNvbSIsImlhdCI6MTY4MjM4MDIzOSwiZXhwIjoxNzAwMzgwMjM5fQ.pxVkCL3bWy_5TN0e2P-iG2zmVXiXAnFOqYv3pohJpLU"
        )

        given(jwtProvider.getToken(user.email))
                .willReturn(tokenResponse)

        val codeRequest = CodeRequest(
                code,
                "device_token"
        )

        loginService.login(
                codeRequest
        )

    }

}