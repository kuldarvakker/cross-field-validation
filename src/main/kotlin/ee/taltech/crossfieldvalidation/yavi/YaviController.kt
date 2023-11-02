package ee.taltech.crossfieldvalidation.yavi

import ee.taltech.crossfieldvalidation.ValidationError
import ee.taltech.crossfieldvalidation.ValidationErrors
import ee.taltech.crossfieldvalidation.common.model.ServiceProvider
import ee.taltech.crossfieldvalidation.yavi.model.yaviPrivatePersonValidator
import ee.taltech.crossfieldvalidation.yavi.model.yaviCompanyValidator
import ee.taltech.crossfieldvalidation.yavi.model.YaviPerson
import ee.taltech.crossfieldvalidation.yavi.model.YaviCompany
import ee.taltech.crossfieldvalidation.yavi.model.YaviPrivatePerson
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class YaviController {

    @PostMapping("/api/yavi")
    fun validatePerson(@Valid @RequestBody person: YaviPerson): ResponseEntity<*> {
        val validationErrors = validate(person)

        return if (validationErrors.errors.isEmpty()) {
            ResponseEntity.status(HttpStatus.OK).body(person)
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors)
        }
    }

    private fun validate(person: YaviPerson): ValidationErrors {
        val results = when(person.type) {
            ServiceProvider.PRIVATE -> yaviPrivatePersonValidator.validate(person as YaviPrivatePerson)
            ServiceProvider.COMPANY -> yaviCompanyValidator.validate(person as YaviCompany)
        }
        return if (!results.isValid) {
            val errors = results.details().map {
                ValidationError(
                    field = it.args[0].toString(),
                    message = it.defaultMessage
                )
            }
            ValidationErrors(errors)
        } else {
            ValidationErrors(emptyList())
        }
    }
}
