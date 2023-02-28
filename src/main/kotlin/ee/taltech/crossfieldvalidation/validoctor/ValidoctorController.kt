package ee.taltech.crossfieldvalidation.validoctor

import ee.taltech.crossfieldvalidation.validoctor.model.Person
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ValidoctorController {

    @PostMapping("/api/validoctor")
    fun validatePerson(@Valid @RequestBody person: Person): ResponseEntity<Person> {

        return ResponseEntity.status(HttpStatus.OK).body(person)
    }

}