package ee.taltech.crossfieldvalidation.valiktor.model

import ee.taltech.crossfieldvalidation.checkNumericValueBounds
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.valiktor.model.attributes.ValiktorAddress
import ee.taltech.crossfieldvalidation.valiktor.model.attributes.ValiktorHeight
import ee.taltech.crossfieldvalidation.valiktor.model.attributes.ValiktorWeight
import ee.taltech.crossfieldvalidation.valiktor.withMessage
import io.swagger.v3.oas.annotations.media.Schema
import org.valiktor.validate
import org.valiktor.functions.hasSize
import org.valiktor.functions.isValid
import org.valiktor.functions.validate


data class ValiktorPrivatePerson(
    @field:Schema(allowableValues = ["PRIVATE"])
    override val type: Agency = Agency.GENERAL,
    @field:Schema(example = "Mari", minLength = 2, maxLength = 4)
    val firstName: String,
    @field:Schema(example = "Maasikas", minLength = 5, maxLength = 10)
    val lastName: String,
    @field:Schema(example = "+37254541010", maxLength = 10)
    val phoneNumber: String?,
    @field:Schema(example = """["email@taltech.ee", "email2@taltech.ee"]""") // TODO define list elem size
    val emails: List<String>?,
    @field:Schema(description = "Person's address")
    val address: ValiktorAddress,
    @field:Schema(description = "Person's height")
    val height: ValiktorHeight,
    @field:Schema(description = "Person's weight")
    val weight: ValiktorWeight
) : ValiktorPerson() {
    init {
        validate(this) { person ->
            validate(ValiktorPrivatePerson::type).isValid { it == Agency.GENERAL }
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
