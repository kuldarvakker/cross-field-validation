package ee.taltech.crossfieldvalidation.yavi

import ee.taltech.crossfieldvalidation.ValidationError
import ee.taltech.crossfieldvalidation.ValidationErrors
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.yavi.model.YaviAgencyForm
import ee.taltech.crossfieldvalidation.yavi.model.company_a.YaviCompanyAAgencyForm
import ee.taltech.crossfieldvalidation.yavi.model.company_a.yaviCompanyAAgencyFormValidator
import ee.taltech.crossfieldvalidation.yavi.model.company_b.YaviCompanyBAgencyForm
import ee.taltech.crossfieldvalidation.yavi.model.company_b.yaviCompanyBAgencyFormValidator
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class YaviController {

    @PostMapping("/api/yavi")
    fun validatePerson(@Valid @RequestBody person: YaviAgencyForm): ResponseEntity<*> {
        val validationErrors = validate(person)

        return if (validationErrors.errors.isEmpty()) {
            ResponseEntity.status(HttpStatus.OK).body(person)
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors)
        }
    }

    private fun validate(person: YaviAgencyForm): ValidationErrors {
        val results = when(person.agency) {
            Agency.GENERAL -> yaviCompanyAAgencyFormValidator.validate(person as YaviCompanyAAgencyForm)
            Agency.COMPANY_A -> yaviCompanyAAgencyFormValidator.validate(person as YaviCompanyAAgencyForm)
            Agency.COMPANY_B -> yaviCompanyBAgencyFormValidator.validate(person as YaviCompanyBAgencyForm)
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
