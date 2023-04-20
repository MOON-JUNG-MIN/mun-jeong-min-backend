package com.example.munjeongminbackend.global.error

import com.example.munjeongminbackend.global.error.custom.CustomException
import com.example.munjeongminbackend.global.error.custom.ErrorResponse
import com.example.munjeongminbackend.global.error.exception.InternalServerException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ExceptionHandler (
        private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: Exception) {
            e.printStackTrace()
            when(e) {
                is CustomException -> handlerException(e, response)
                else -> handlerException(InternalServerException, response)
            }
        }
    }

    private fun handlerException(e: CustomException, response: HttpServletResponse) {
        val error = ErrorResponse.custom(e)

        response.status = error.status
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(objectMapper.writeValueAsString(error))
    }
}