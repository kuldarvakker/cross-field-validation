package ee.taltech.crossfieldvalidation

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorControllerAdvice {

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun processConversionException(e: HttpMessageNotReadableException): ValidationErrors {
        var msg: String? = null
        val cause = e.cause
        if (cause is JsonParseException) {
            val jpe: JsonParseException = cause
            msg = jpe.originalMessage
        } else if (cause is MismatchedInputException) {
            val mie = cause
            msg = if (mie.path != null && mie.path.size > 0) {
                "Invalid request field: " + mie.path[0].fieldName
            } else {
                "Invalid request message"
            }
        } else if (cause is JsonMappingException) {
            val jme = cause
            msg = jme.originalMessage
            if (jme.path != null && jme.path.size > 0) {
                msg = "Invalid request field: " + jme.path[0].fieldName +
                        ": " + msg
            }
        }
        return ValidationErrors(listOf(ValidationError("constraint", msg!!)))
    }
}