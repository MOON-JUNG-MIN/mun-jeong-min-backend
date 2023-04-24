package com.example.munjeongminbackend.global.security.jwt

import com.example.munjeongminbackend.domain.user.present.dto.TokenResponse
import com.example.munjeongminbackend.global.error.exception.ExpiredJwtException
import com.example.munjeongminbackend.global.error.exception.InValidJwtException
import com.example.munjeongminbackend.global.security.auth.AuthDetailsService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtProvider (
        private val jwtProperties: JwtProperties,
        private val authDetailsService: AuthDetailsService
) {

    fun getToken(id: String): TokenResponse {
        return TokenResponse(
                accessToken = accessToken(id, jwtProperties.accessExp, "access")
        )
    }

    private fun accessToken(id: String, expired: Long, type: String): String {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
                .setSubject(id)
                .setHeaderParam("type", type)
                .setIssuedAt(Date())
                .setExpiration(Date(System.currentTimeMillis() + expired * 1000))
                .compact()
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val token = request.getHeader(jwtProperties.header)
        return parseToken(token)
    }

    fun parseToken(token: String?) : String? {
        return if(token != null && token.startsWith(jwtProperties.prefix)) {
            token.replace(jwtProperties.prefix, "")
        } else null
    }

    fun authentication(token: String): Authentication {
        val userDetails: UserDetails = authDetailsService.loadUserByUsername(getTokenBody(token))

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getTokenBody(token: String): String {
        return try {
            Jwts.parser().setSigningKey(jwtProperties.secretKey)
                    .parseClaimsJws(token).body.subject
        } catch (e: Exception) {
            e.printStackTrace()
            when (e) {
                is io.jsonwebtoken.ExpiredJwtException -> throw ExpiredJwtException.EXCEPTION
                else -> throw InValidJwtException.EXCEPTION
            }
        }
    }

}