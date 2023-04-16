package ee.taltech.crossfieldvalidation.valiktor

import ee.taltech.crossfieldvalidation.ValidationError
import ee.taltech.crossfieldvalidation.ValidationErrors
import ee.taltech.crossfieldvalidation.valiktor.model.Person
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.valiktor.ConstraintViolationException
import org.valiktor.i18n.ConstraintViolationMessage
import org.valiktor.i18n.mapToMessage
import java.util.*

@RestController
class ValiktorController {

    @PostMapping("/api/valiktor")
    fun validatePerson(@RequestBody person: Person): ResponseEntity<Person> {

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