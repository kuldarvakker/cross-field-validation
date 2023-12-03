package ee.taltech.crossfieldvalidation.validoctor

import com.miquido.validoctor.Validoctor
import com.miquido.validoctor.result.DiagnosisException
import ee.taltech.crossfieldvalidation.ValidationError
import ee.taltech.crossfieldvalidation.ValidationErrors
import ee.taltech.crossfieldvalidation.validoctor.model.ValidoctorAgencyForm
import ee.taltech.crossfieldvalidation.validoctor.model.company_a.ValidoctorCompanyAAgencyForm
import ee.taltech.crossfieldvalidation.validoctor.model.company_a.validoctorCompanyAAgencyFormValidator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class ValidoctorController {

    @PostMapping("/api/validoctor")
    fun validatePerson(@RequestBody person: ValidoctorAgencyForm): ResponseEntity<ValidoctorAgencyForm> {
        Validoctor.setThrowing(true)
        Validoctor.examine(person as ValidoctorCompanyAAgencyForm, validoctorCompanyAAgencyFormValidator)
        return ResponseEntity.status(HttpStatus.OK).body(person)
    }

    @ExceptionHandler(DiagnosisException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationException(e: DiagnosisException): ValidationErrors {
        val errors = e.diagnosis.ailments.map { ValidationError(it.key, it.value.first()) }.toList()
        return ValidationErrors(errors)
    }
}
