package ee.taltech.crossfieldvalidation.java_fluent

import br.com.fluentvalidator.Validator
import ee.taltech.crossfieldvalidation.ValidationError
import ee.taltech.crossfieldvalidation.ValidationErrors
import ee.taltech.crossfieldvalidation.java_fluent.model.Company
import ee.taltech.crossfieldvalidation.java_fluent.model.Person
import ee.taltech.crossfieldvalidation.java_fluent.model.PersonType
import ee.taltech.crossfieldvalidation.java_fluent.model.PrivatePerson
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@RestController
class JavaFluentController {

    private final val privatePersonValidator: Validator<PrivatePerson> = PrivatePersonValidator()
    private final val companyValidator: Validator<Company> = CompanyValidator()

    @PostMapping("/api/java_fluent")
    fun validatePerson(@RequestBody person: Person): ResponseEntity<*> {
        val results = when (person.type) {
            PersonType.PRIVATE -> privatePersonValidator.validate(person as PrivatePerson)
            PersonType.COMPANY -> companyValidator.validate(person as Company)
        }
        if (!results.isValid) {
            val errors = results.errors.map {
                ValidationError(
                    field =it.field ?: "null code",
                    message = it.message
                )
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationErrors(errors))
        }

        return ResponseEntity.status(HttpStatus.OK).body(person)

    }
}
