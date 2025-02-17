package org.economic.statistics.common.exception

import org.economic.statistics.types.global.GlobalResponse
import org.economic.statistics.types.global.Result
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException


@ControllerAdvice
class GlobalException {

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentTypeMismatch(ex: MethodArgumentTypeMismatchException): ResponseEntity<Result<Any>> {
        val errorMessage = "Invalid value for parameter '" + ex.name

        val errorResponse: Result<Any> = BadRequest(errorMessage)

        return ResponseEntity<Result<Any>>(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMissingParams(ex: MissingServletRequestParameterException): ResponseEntity<Result<Any>> {
        val errorMessage = "Required parameter '" + ex.parameterName + "' is missing"

        val errorResponse: Result<Any> = BadRequest(errorMessage)

        return ResponseEntity<Result<Any>>(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: CustomException): ResponseEntity<Result<Any>> {
        val codeInterface = ex.getCodeInterface()
        val errorMessage = codeInterface.message
        val errorCode = codeInterface.code

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(GlobalResponse.customError(errorCode, errorMessage))
    }


    private fun BadRequest(msg: String): Result<Any> {
        return GlobalResponse.error(
            HttpStatus.BAD_REQUEST,
            msg
        )
    }
}