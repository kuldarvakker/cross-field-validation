package ee.taltech.crossfieldvalidation.konform.model

import ee.taltech.crossfieldvalidation.checkNumericValueBounds
import ee.taltech.crossfieldvalidation.common.model.PersonType
import ee.taltech.crossfieldvalidation.konform.model.attributes.KonformAddress
import ee.taltech.crossfieldvalidation.konform.model.attributes.KonformHeight
import ee.taltech.crossfieldvalidation.konform.model.attributes.KonformWeight

import io.konform.validation.Validation
import io.konform.validation.jsonschema.enum
import io.konform.validation.jsonschema.maxLength
import io.konform.validation.jsonschema.minItems
import io.konform.validation.jsonschema.minLength
import io.swagger.v3.oas.annotations.media.Schema

data class KonformPrivatePerson(
    @field:Schema(allowableValues = ["PRIVATE"])
    override val type: PersonType = PersonType.PRIVATE,
    @field:Schema(example = "Mari", minLength = 2, maxLength = 4)
    val firstName: String,
    @field:Schema(example = "Maasikas", minLength = 5, maxLength = 10)
    val lastName: String,
    @field:Schema(example = "+37254541010", maxLength = 10)
    val phoneNumber: String?,
    @field:Schema(example = """["email@taltech.ee", "email2@taltech.ee"]""") // TODO define list elem size
    val emails: List<String>?,
    @field:Schema(description = "Person's address")
    val address: KonformAddress,
    @field:Schema(description = "Person's height")
    val height: KonformHeight,
    @field:Schema(description = "Person's weight")
    val weight: KonformWeight
) : KonformPerson() {

    companion object {
        val validate = Validation<KonformPrivatePerson> {
            KonformPrivatePerson::type required { enum(PersonType.PRIVATE) }
            KonformPrivatePerson::firstName required {
                minLength(2)
                maxLength(4)
            }
            KonformPrivatePerson::lastName required {
                minLength(5)
                maxLength(10)
            }
            KonformPrivatePerson::phoneNumber ifPresent {
                maxLength(10)
            }
            KonformPrivatePerson::emails ifPresent {
                minItems(0)
            }
            KonformPrivatePerson::address required {
                KonformAddress::city required { minLength(2) }
                KonformAddress::state required { minLength(2) }
                KonformAddress::street required { minLength(2) }
                KonformAddress::postalCode required { minLength(2) }
                KonformAddress::country required { minLength(2) }
            }
            KonformPrivatePerson::weight required {
                KonformWeight::value required {
                    addConstraint("numeric value out of bounds (<3 digits>.<2 digits> expected)") {
                        checkNumericValueBounds(it, 3,2)
                    }
                }
            }
            KonformPrivatePerson::height required {
                KonformHeight::value required {
                    addConstraint("numeric value out of bounds (<3 digits>.<2 digits> expected)") {
                        checkNumericValueBounds(it, 3,2)
                    }
                }
            }

            addConstraint("Phone or Email must be present") { person ->
                (!person.phoneNumber.isNullOrBlank() || !person.emails.isNullOrEmpty())
            }
        }
    }
}
