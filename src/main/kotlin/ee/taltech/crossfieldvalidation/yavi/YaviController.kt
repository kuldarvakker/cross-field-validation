package ee.taltech.crossfieldvalidation.yavi

import ee.taltech.crossfieldvalidation.ValidationError
import ee.taltech.crossfieldvalidation.ValidationErrors
import ee.taltech.crossfieldvalidation.yavi.model.privatePersonValidator
import ee.taltech.crossfieldvalidation.yavi.model.companyValidator
import ee.taltech.crossfieldvalidation.yavi.model.Person
import ee.taltech.crossfieldvalidation.yavi.model.Company
import ee.taltech.crossfieldvalidation.yavi.model.PrivatePerson
import ee.taltech.crossfieldvalidation.yavi.model.PersonType
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class YaviController {

    @PostMapping("/api/yavi")
    fun validatePerson(@Valid @RequestBody person: Person): ResponseEntity<*> {
        val results = when(person.type) {
            PersonType.PRIVATE -> privatePersonValidator.validate(person as PrivatePerson)
            PersonType.COMPANY -> companyValidator.validate(person as Company)
        }
        if (!results.isValid) {
            val errors = results.details().map {
                ValidationError(
                    field = it.args[0].toString(),
                    message = it.defaultMessage
                )
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationErrors(errors))
        }

        return ResponseEntity.status(HttpStatus.OK).body(person)
    }

}