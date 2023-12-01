package ee.taltech.crossfieldvalidation.kalidation

import ee.taltech.crossfieldvalidation.ValidationError
import ee.taltech.crossfieldvalidation.ValidationErrors
import ee.taltech.crossfieldvalidation.kalidation.model.KalidationAgencyForm
import ee.taltech.crossfieldvalidation.kalidation.model.company_a.KalidationCompanyAAgencyForm
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class KalidationController {

    @PostMapping("/api/kalidation")
    fun validatePerson(@RequestBody person: KalidationAgencyForm): ResponseEntity<*> {
        val a = person as KalidationCompanyAAgencyForm
        val errors = emptyList<ValidationError>()
        return if (errors.isEmpty()) {
            ResponseEntity.status(HttpStatus.OK).body(person)
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationErrors(errors))
        }
    }
}
