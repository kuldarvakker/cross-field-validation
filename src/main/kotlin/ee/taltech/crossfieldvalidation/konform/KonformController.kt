package ee.taltech.crossfieldvalidation.konform

import ee.taltech.crossfieldvalidation.ValidationError
import ee.taltech.crossfieldvalidation.ValidationErrors
import ee.taltech.crossfieldvalidation.konform.model.Company
import ee.taltech.crossfieldvalidation.konform.model.Person
import ee.taltech.crossfieldvalidation.konform.model.PersonType
import ee.taltech.crossfieldvalidation.konform.model.PrivatePerson
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class KonformController {

    @PostMapping("/api/konform")
    fun validatePerson(@Valid @RequestBody person: Person): ResponseEntity<*> {
        val validationErrors = validate(person)

        return if (validationErrors.errors.isEmpty()) {
            ResponseEntity.status(HttpStatus.OK).body(person)
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors)
        }
    }

    private fun validate(person: Person): ValidationErrors {
        val results = when (person.type) {
            PersonType.PRIVATE -> PrivatePerson.validate(person as PrivatePerson)
            PersonType.COMPANY -> Company.validate(person as Company)
        }
        return if (results.errors.isNotEmpty()) {
            val errors = results.errors.map {
                ValidationError(
                    field = it.dataPath,
                    message = it.message
                )
            }
            ValidationErrors(errors)
        } else {
            ValidationErrors(emptyList())
        }
    }
}
