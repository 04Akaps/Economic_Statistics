package org.economic.statistics.types.global

import org.springframework.http.HttpStatus

object GlobalResponse {
    fun <T> success(result: T): Result<T> {
        return Result(HttpStatus.OK.value(), SUCCESS, result)
    }

    fun <T> error(code: HttpStatus, message: String, result : T? = null): Result<T> {
        return Result(code.value(), message, result)
    }

    fun <T> customError(code: Int, message: String, result : T? = null): Result<T> {
        return Result(code, message, result)
    }
}

data class Result<T>(
    val code: Int,
    val message: String?,
    val result: T?
)


const val SUCCESS = "SUCCESS"