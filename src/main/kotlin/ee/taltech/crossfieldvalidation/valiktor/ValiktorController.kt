package ee.taltech.crossfieldvalidation.valiktor

import ee.taltech.crossfieldvalidation.ValidationError
import ee.taltech.crossfieldvalidation.ValidationErrors
import ee.taltech.crossfieldvalidation.valiktor.model.ValiktorAgencyForm
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.valiktor.ConstraintViolationException
import org.valiktor.i18n.ConstraintViolationMessage
import org.valiktor.i18n.mapToMessage
import java.util.Locale

@RestController
class ValiktorController {

    @PostMapping("/api/valiktor")
    fun validatePerson(@RequestBody person: ValiktorAgencyForm): ResponseEntity<ValiktorAgencyForm> {
        return ResponseEntity.status(HttpStatus.OK).body(person)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationException(e: ConstraintViolationException): ValidationErrors {
        val fieldErrors = e.constraintViolations.mapToMessage(baseName = "messages", locale = Locale.ENGLISH)
        val reasons =  fieldErrors.map {
            mapToValidationError(it)
        }.toList()

        return ValidationErrors(reasons)
    }

    private fun mapToValidationError(fieldError: ConstraintViolationMessage): ValidationError {
        return ValidationError(
            field = fieldError.property,
            message = fieldError.message
        )
    }
}
