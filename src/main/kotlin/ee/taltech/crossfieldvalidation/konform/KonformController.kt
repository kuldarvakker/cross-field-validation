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
        val results = when (person.type) {
            PersonType.PRIVATE -> PrivatePerson.validate(person as PrivatePerson)
            PersonType.COMPANY -> Company.validate(person as Company)
        }
        if (results.errors.isNotEmpty()) {
            val errors = results.errors.map {
                ValidationError(
                    field =it.dataPath,
                    message = it.message
                )
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationErrors(errors))
        }

        return ResponseEntity.status(HttpStatus.OK).body(person)
    }

}
