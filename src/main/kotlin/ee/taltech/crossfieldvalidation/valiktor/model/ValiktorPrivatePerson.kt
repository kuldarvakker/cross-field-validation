package ee.taltech.crossfieldvalidation.valiktor.model

import ee.taltech.crossfieldvalidation.checkNumericValueBounds
import ee.taltech.crossfieldvalidation.common.model.PersonType
import ee.taltech.crossfieldvalidation.valiktor.model.attributes.ValiktorAddress
import ee.taltech.crossfieldvalidation.valiktor.model.attributes.ValiktorHeight
import ee.taltech.crossfieldvalidation.valiktor.model.attributes.ValiktorWeight
import ee.taltech.crossfieldvalidation.valiktor.withMessage
import org.valiktor.validate
import org.valiktor.functions.hasSize
import org.valiktor.functions.isValid
import org.valiktor.functions.validate


data class ValiktorPrivatePerson(
    override val type: PersonType = PersonType.PRIVATE,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?,
    val emails: List<String>?,
    val address: ValiktorAddress,
    val height: ValiktorHeight,
    val weight: ValiktorWeight
) : ValiktorPerson() {
    init {
        validate(this) { person ->
            validate(ValiktorPrivatePerson::type).isValid { it == PersonType.PRIVATE }
            validate(ValiktorPrivatePerson::firstName).hasSize(min = 2, max = 4)
            validate(ValiktorPrivatePerson::lastName).hasSize(min = 5, max = 10)
            validate(ValiktorPrivatePerson::phoneNumber).withMessage("Phone or Email must be present") {
                (!it.isNullOrBlank() || !person.emails.isNullOrEmpty())
            }
            validate(ValiktorPrivatePerson::phoneNumber).hasSize(max = 10)
            validate(ValiktorPrivatePerson::address).validate {
                validate(ValiktorAddress::street).hasSize(min = 2)
                validate(ValiktorAddress::city).hasSize(min = 2)
                validate(ValiktorAddress::state).hasSize(min = 2)
                validate(ValiktorAddress::country).hasSize(min = 2)
                validate(ValiktorAddress::postalCode).hasSize(min = 2)
            }
            validate(ValiktorPrivatePerson::weight).validate {
                validate(ValiktorWeight::value).withMessage("numeric value out of bounds (<3 digits>.<2 digits> expected)") { value ->
                    value?.let { checkNumericValueBounds(it, 3,2) } ?: true
                }
            }
            validate(ValiktorPrivatePerson::height).validate {
                validate(ValiktorHeight::value).withMessage("numeric value out of bounds (<3 digits>.<2 digits> expected)") { value ->
                    value?.let { checkNumericValueBounds(it, 3,2) } ?: true
                }
//                validate(Height::value).include(Height::unit).withMessage("custom message") { value, unit ->
//                    value?.let { checkNumericValueBounds(it, 3,2) && unit != null && unit == Units.CM } ?: true
//                }
            }
            // validate(PrivatePerson::height).withFieldName("Abc") {height ->
            //     validate(Height::value).withMessage("numeric value out of bounds (<3 digits>.<2 digits> expected)") { value ->
            //         value?.let { checkNumericValueBounds(it, 3,2) } ?: true
            //     }
            // }
        }
    }
}
