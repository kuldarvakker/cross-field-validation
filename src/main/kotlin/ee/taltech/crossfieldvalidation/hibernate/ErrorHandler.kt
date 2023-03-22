package ee.taltech.crossfieldvalidation.hibernate

import ee.taltech.crossfieldvalidation.ValidationError
import ee.taltech.crossfieldvalidation.ValidationErrors
import org.springframework.context.support.DefaultMessageSourceResolvable
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*
import java.util.stream.Collectors
import kotlin.Any


@RestControllerAdvice
class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleErrors(e: MethodArgumentNotValidException): ValidationErrors {
        val fieldErrors = e.bindingResult.fieldErrors
        val reasons = fieldErrors
            .map { mapToValidationError(it) }
            .toList()

        return ValidationErrors(reasons)
    }

    private fun mapToValidationError(fieldError: FieldError): ValidationError {
        val code = fieldError.codes!![0]
        return ValidationError(
            field = fieldError.field,
            message = fieldError.defaultMessage ?: "Message not available"
        )
    }

}

