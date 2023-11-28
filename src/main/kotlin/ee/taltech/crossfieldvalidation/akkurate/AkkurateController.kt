package ee.taltech.crossfieldvalidation.akkurate

import dev.nesk.akkurate.ValidationResult
import ee.taltech.crossfieldvalidation.ValidationError
import ee.taltech.crossfieldvalidation.ValidationErrors
import ee.taltech.crossfieldvalidation.akkurate.model.AkkurateAgencyForm
import ee.taltech.crossfieldvalidation.akkurate.model.company_a.AkkurateCompanyAAgencyForm
import ee.taltech.crossfieldvalidation.akkurate.model.company_a.validateAkkurateCompanyAAgencyForm
import ee.taltech.crossfieldvalidation.common.model.Agency
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AkkurateController {

    @PostMapping("/api/akkurate")
    fun validatePerson(@RequestBody form: AkkurateAgencyForm): ResponseEntity<*> {
        val validationErrors = validate(form)

        return if (validationErrors.errors.isEmpty()) {
            ResponseEntity.status(HttpStatus.OK).body(form)
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors)
        }
    }

    private fun validate(form: AkkurateAgencyForm): ValidationErrors {
         val results = when (form.agency) {
            Agency.GENERAL -> validateAkkurateCompanyAAgencyForm(form as AkkurateCompanyAAgencyForm)
            Agency.COMPANY_A -> validateAkkurateCompanyAAgencyForm(form as AkkurateCompanyAAgencyForm)
            Agency.COMPANY_B -> validateAkkurateCompanyAAgencyForm(form as AkkurateCompanyAAgencyForm)
        }
        val errors = when (results) {
            is ValidationResult.Success -> emptyList()
            is ValidationResult.Failure -> results.violations.map { ValidationError(
                field = "${it.path.joinToString(".")}",
                message = it.message
            ) }.toList()
        }

        return ValidationErrors(errors)
    }
}
