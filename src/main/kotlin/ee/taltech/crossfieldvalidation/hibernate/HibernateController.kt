package ee.taltech.crossfieldvalidation.hibernate

import ee.taltech.crossfieldvalidation.hibernate.model.Person
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@Validated
class HibernateController {

    @PostMapping("/api/hibernate")
    fun validatePerson(@Valid @RequestBody person: Person): ResponseEntity<Person> {

        return ResponseEntity.status(HttpStatus.OK).body(person)
    }

}