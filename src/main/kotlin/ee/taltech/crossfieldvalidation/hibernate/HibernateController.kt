package ee.taltech.crossfieldvalidation.hibernate

import ee.taltech.crossfieldvalidation.ValidationError
import ee.taltech.crossfieldvalidation.ValidationErrors
import ee.taltech.crossfieldvalidation.hibernate.model.HibernatePerson
import jakarta.validation.Validation
import jakarta.validation.Validator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping

@RestController
class HibernateController {

    private val validator = getValidator()

    @PostMapping("/api/hibernate")
    fun validatePerson(@RequestBody person: HibernatePerson): ResponseEntity<*> {
        val validationErrors = validate(person)

        return if (validationErrors.errors.isEmpty()) {
            ResponseEntity.status(HttpStatus.OK).body(person)
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors)
        }
    }

    private fun getValidator(): Validator {
        val factory = Validation.buildDefaultValidatorFactory()
        return factory.validator
    }

    private fun validate(person: HibernatePerson): ValidationErrors {
        val results = validator.validate(person)
        return if (results.isNotEmpty()) {
            val errors = results.map {
                ValidationError(
                    field = it.propertyPath.toString(),
                    message = it.message
                )
            }
            ValidationErrors(errors)
        } else {
            ValidationErrors(emptyList())
        }
    }
}
