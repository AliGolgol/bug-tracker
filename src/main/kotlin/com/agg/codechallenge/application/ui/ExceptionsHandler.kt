package com.agg.codechallenge.application.ui

import com.agg.codechallenge.application.ui.responses.share.ApiError
import com.agg.codechallenge.application.ui.responses.share.ApiErrors
import com.agg.codechallenge.domain.bugtracker.exceptions.BugTrackerNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import java.util.*

/**
 * This class contains all exception handling logic for the API.
 * It is responsible to translate thrown exceptions to well-structured API responses.
 * Each method annotated with `@ExceptionHandler` is responsible for handling related exceptions.
 **/
@RestControllerAdvice
class ExceptionsHandler(@Autowired private val messageSource: MessageSource) {

    private val logger = LoggerFactory.getLogger(ExceptionsHandler::class.java)

    @ExceptionHandler(Exception::class)
    fun handleInvalidInputException(ex: Exception, request: WebRequest, locale: Locale) =
        responseWithApiError(
            HttpStatus.BAD_REQUEST,
            messageSource.getMessage("invalid-input", null, locale),
            null,
            ex
        )

    @ExceptionHandler(BugTrackerNotFoundException::class)
    fun handleNotFoundException(
        ex: Exception,
        request: WebRequest,
        locale: Locale
    ) = responseWithApiError(HttpStatus.NOT_FOUND, ex, locale)

    fun responseWithApiError(
        status: HttpStatus,
        message: String,
        detail: String?,
        exception: Throwable
    ): ResponseEntity<ApiErrors> {
        logger.error(message, exception)
        return ResponseEntity(
            ApiErrors(
                listOf(ApiError(UUID.randomUUID().toString(), status.toString(), message, detail))
            ),
            status
        )
    }

    fun responseWithApiError(
        status: HttpStatus,
        exception: Throwable,
        locale: Locale
    ): ResponseEntity<ApiErrors> {
        logger.info("response with api error")
        val message =
            messageSource.getMessage(exception.message ?: "", exception.stackTrace, locale)
        return responseWithApiError(status, message, null, exception)
    }
}
