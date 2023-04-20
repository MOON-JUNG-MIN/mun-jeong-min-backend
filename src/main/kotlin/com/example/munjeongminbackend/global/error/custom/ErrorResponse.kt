package com.example.munjeongminbackend.global.error.custom

class ErrorResponse<T> (
        val status: Int,
        val message: String
) {
    companion object {
        fun custom(e: CustomException):ErrorResponse<Unit> = ErrorResponse(
                status = e.status,
                message = e.errorMessage
        )

        fun valid(status: Int, message: String):ErrorResponse<Unit> = ErrorResponse(
                status = status,
                message = message
        )
    }
}