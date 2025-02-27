package org.economic.statistics.common.config

import io.micrometer.common.lang.Nullable
import jakarta.servlet.http.HttpServletRequest
import org.economic.statistics.common.exception.CustomException
import org.economic.statistics.common.exception.ErrorCode
import org.economic.statistics.custom.interfaces.PGMKeyRequest
import org.economic.statistics.types.const.PGMList
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import java.util.*

@Component
class WebRequestConfig: HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(PGMKeyRequest::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        @Nullable mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        @Nullable binderFactory: WebDataBinderFactory?
    ): Any? {

        if (parameter.hasParameterAnnotation(PGMKeyRequest::class.java)) {
            val request: HttpServletRequest = webRequest.getNativeRequest(HttpServletRequest::class.java)

            val path = request.requestURI
            val pathSegments = path.split("/")

            val pgmIndex = pathSegments.indexOf("confirm-pay") + 1
            if (pgmIndex <= 0 || pgmIndex >= pathSegments.size) {
                throw CustomException(ErrorCode.PGMKeyRequired) // PGM 값이 없으면 예외 발생
            }

            val pgm = pathSegments[pgmIndex]

            if (!PGMList.isValidPGMType(pgm)) {
                throw CustomException(ErrorCode.NotSupportedPGMKeyRequest, pgm)
            }

            return pgm
        }

        return null
    }
}