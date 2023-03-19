package ee.taltech.crossfieldvalidation.hibernate

import ee.taltech.crossfieldvalidation.hibernate.model.Person
import jakarta.validation.Valid
import jakarta.validation.Validation
import jakarta.validation.Validator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping

@RestController
@Validated
class HibernateController {

    private val validator = getValidator()

    @PostMapping("/api/hibernate")
    fun validatePerson(@Valid @RequestBody person: Person): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.OK).body(person)
    }

    private final fun getValidator(): Validator {
        val factory = Validation.buildDefaultValidatorFactory()
        return factory.validator
    }
}
