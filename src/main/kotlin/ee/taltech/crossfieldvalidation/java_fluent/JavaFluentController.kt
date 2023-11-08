package ee.taltech.crossfieldvalidation.java_fluent

import br.com.fluentvalidator.Validator
import ee.taltech.crossfieldvalidation.ValidationError
import ee.taltech.crossfieldvalidation.ValidationErrors
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.java_fluent.model.JavaFluentAgencyForm
import ee.taltech.crossfieldvalidation.java_fluent.model.JavaFluentCompany
import ee.taltech.crossfieldvalidation.java_fluent.model.JavaFluentPrivatePerson
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@RestController
class JavaFluentController {

    private final val privatePersonValidator: Validator<JavaFluentPrivatePerson> = JavaFluentPrivatePersonValidator()
    private final val companyValidator: Validator<JavaFluentCompany> = JavaFluentCompanyValidator()

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
            Agency.GENERAL -> privatePersonValidator.validate(form as JavaFluentPrivatePerson)
            Agency.COMPANY_A -> companyValidator.validate(form as JavaFluentCompany)
            Agency.COMPANY_B -> companyValidator.validate(form as JavaFluentCompany)
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
