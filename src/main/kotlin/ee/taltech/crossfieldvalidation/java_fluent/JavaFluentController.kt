package ee.taltech.crossfieldvalidation.java_fluent

import br.com.fluentvalidator.Validator
import ee.taltech.crossfieldvalidation.ValidationError
import ee.taltech.crossfieldvalidation.ValidationErrors
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.java_fluent.model.JavaFluentAgencyForm
import ee.taltech.crossfieldvalidation.java_fluent.model.company_a.JavaFluentCompanyAAgencyForm
import ee.taltech.crossfieldvalidation.java_fluent.model.company_b.JavaFluentCompanyBAgencyForm
import ee.taltech.crossfieldvalidation.java_fluent.model.general.JavaFluentGeneralAgencyForm
import ee.taltech.crossfieldvalidation.java_fluent.validator.JavaFluentCompanyAValidator
import ee.taltech.crossfieldvalidation.java_fluent.validator.JavaFluentCompanyBValidator
import ee.taltech.crossfieldvalidation.java_fluent.validator.JavaFluentGeneralValidator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@RestController
class JavaFluentController {

    private final val generalValidator: Validator<JavaFluentGeneralAgencyForm> = JavaFluentGeneralValidator()
    private final val companyAValidator: Validator<JavaFluentCompanyAAgencyForm> = JavaFluentCompanyAValidator()
    private final val companyBValidator: Validator<JavaFluentCompanyBAgencyForm> = JavaFluentCompanyBValidator()

    @PostMapping("/api/java_fluent")
    fun validatePerson(@RequestBody person: JavaFluentAgencyForm): ResponseEntity<*> {
        val validationErrors = validate(person)

        return if (validationErrors.errors.isEmpty()) {
            ResponseEntity.status(HttpStatus.OK).body(person)
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors)
        }
    }

    private fun validate(form: JavaFluentAgencyForm): ValidationErrors {
        val results = when (form.agency) {
            Agency.GENERAL -> generalValidator.validate(form as JavaFluentGeneralAgencyForm)
            Agency.COMPANY_A -> companyAValidator.validate(form as JavaFluentCompanyAAgencyForm)
            Agency.COMPANY_B -> companyBValidator.validate(form as JavaFluentCompanyBAgencyForm)
        }
        return if (!results.isValid) {
            val errors = results.errors.map {
                ValidationError(
                    field =it.field ?: "null code",
                    message = it.message
                )
            }
            ValidationErrors(errors)
        } else {
            ValidationErrors(emptyList())
        }
    }
}
