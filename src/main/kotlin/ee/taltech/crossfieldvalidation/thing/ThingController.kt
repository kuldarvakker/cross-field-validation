package ee.taltech.crossfieldvalidation.thing

import ee.taltech.crossfieldvalidation.ValidationError
import ee.taltech.crossfieldvalidation.ValidationErrors
import ee.taltech.crossfieldvalidation.thing.model.ThingAgencyForm
import ee.taltech.crossfieldvalidation.thing.model.company_a.ThingCompanyAAgencyForm
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ThingController {

    @PostMapping("/api/thing")
    fun validatePerson(@RequestBody person: ThingAgencyForm): ResponseEntity<*> {
        val a = person as ThingCompanyAAgencyForm
        val errors = a.validate().errors.map {
            mapToValidationError(it)
        }
        return if (errors.isEmpty()) {
            ResponseEntity.status(HttpStatus.OK).body(person)
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationErrors(errors))
        }
    }


    private fun mapToValidationError(error: so.kciter.thing.validator.ValidationError): ValidationError {
        return ValidationError(
            field = error.dataPath,
            message = error.message
        )
    }
}
