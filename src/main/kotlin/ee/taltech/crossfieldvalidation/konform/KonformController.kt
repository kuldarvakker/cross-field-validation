package ee.taltech.crossfieldvalidation.konform

import ee.taltech.crossfieldvalidation.ValidationError
import ee.taltech.crossfieldvalidation.ValidationErrors
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.konform.model.KonformAgencyForm
import ee.taltech.crossfieldvalidation.konform.model.company_a.KonformCompanyAAgencyForm
import ee.taltech.crossfieldvalidation.konform.model.company_b.KonformCompanyBAgencyForm
import ee.taltech.crossfieldvalidation.konform.model.general.KonformGeneralAgencyForm
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class KonformController {

    @PostMapping("/api/konform")
    fun validatePerson(@RequestBody form: KonformAgencyForm): ResponseEntity<*> {
        val validationErrors = validate(form)

        return if (validationErrors.errors.isEmpty()) {
            ResponseEntity.status(HttpStatus.OK).body(form)
        } else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors)
        }
    }

    private fun validate(form: KonformAgencyForm): ValidationErrors {
//        val results = when (form.agency) {
//            Agency.GENERAL -> // TODO((form as KonformGeneralAgencyForm)
//            Agency.COMPANY_A -> // TODO(form as KonformCompanyAAgencyForm)
//            Agency.COMPANY_B -> // TODO(form as KonformCompanyBAgencyForm)
//        }
//        return if (results.errors.isNotEmpty()) {
//            val errors = results.errors.map {
//                ValidationError(
//                    field = it.dataPath,
//                    message = it.message
//                )
//            }
//            ValidationErrors(errors)
//        } else {
//            ValidationErrors(emptyList())
//        }

        return ValidationErrors(emptyList())
    }
}
