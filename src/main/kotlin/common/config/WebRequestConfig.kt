package org.economic.statistics.common.config

import io.micrometer.common.lang.Nullable
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
            webRequest.getParameter(Objects.requireNonNull(parameter.parameterName))?.let {
                if (!PGMList.isValidPGMType(it)) {
                    // TODO Exception
                }

                return it
            } ?: run {
                // TODO Exception
            }
        }

        return null
    }
}