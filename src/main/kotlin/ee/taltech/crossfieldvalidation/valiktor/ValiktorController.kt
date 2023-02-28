package ee.taltech.crossfieldvalidation.valiktor

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import ee.taltech.crossfieldvalidation.valiktor.model.Person
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.*

@RestController
class ValiktorController {

    @PostMapping("/api/valiktor")
    fun validatePerson(@Valid @RequestBody person: Person): ResponseEntity<Person> {

        return ResponseEntity.status(HttpStatus.OK).body(person)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationException(e: MissingKotlinParameterException): String {
        return e.parameter.name.toString()
    }
}