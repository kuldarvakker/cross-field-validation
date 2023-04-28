package ee.taltech.crossfieldvalidation.konform.model

import ee.taltech.crossfieldvalidation.konform.model.attributes.Address
import ee.taltech.crossfieldvalidation.konform.model.attributes.Height
import ee.taltech.crossfieldvalidation.konform.model.attributes.Weight

import io.konform.validation.Validation
import io.konform.validation.jsonschema.enum
import io.konform.validation.jsonschema.maxLength
import io.konform.validation.jsonschema.minItems
import io.konform.validation.jsonschema.minLength

data class PrivatePerson(
    override val type: PersonType = PersonType.PRIVATE,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?,
    val emails: List<String>?,
    val address: Address,
    val height: Height,
    val weight: Weight
) : Person() {

    companion object {
        val validate = Validation<PrivatePerson> {
            PrivatePerson::type required { enum(PersonType.PRIVATE) }
            PrivatePerson::firstName required {
                minLength(2)
                maxLength(4)
            }
            PrivatePerson::lastName required {
                minLength(5)
                maxLength(10)
            }
            PrivatePerson::phoneNumber ifPresent {
                maxLength(10)
            }
            PrivatePerson::emails ifPresent {
                minItems(0)
            }
            PrivatePerson::address required {
                Address::city required { minLength(2) }
                Address::state required { minLength(2) }
                Address::street required { minLength(2) }
                Address::postalCode required { minLength(2) }
                Address::country required { minLength(2) }
            }
            PrivatePerson::weight required {
                Weight::value required {
                    addConstraint("numeric value out of bounds (<3 digits>.<2 digits> expected)") {
                        it.scale() < 2 || it.precision() - it.scale() < 3
                    }
                }
            }
            PrivatePerson::height required {
                Height::value required {
                    addConstraint("numeric value out of bounds (<3 digits>.<2 digits> expected)") {
                        it.scale() < 2 || it.precision() - it.scale() < 3
                    }
                }
            }

            addConstraint("Phone or Email must be present") { person ->
                (!person.phoneNumber.isNullOrBlank() || !person.emails.isNullOrEmpty())
            }
        }
    }
}
