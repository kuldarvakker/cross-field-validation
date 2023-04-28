package ee.taltech.crossfieldvalidation.valiktor.model

import ee.taltech.crossfieldvalidation.checkNumericValueBounds
import ee.taltech.crossfieldvalidation.valiktor.model.attributes.Address
import ee.taltech.crossfieldvalidation.valiktor.model.attributes.Height
import ee.taltech.crossfieldvalidation.valiktor.model.attributes.Weight
import org.valiktor.*
import org.valiktor.functions.hasSize
import org.valiktor.functions.isValid
import org.valiktor.functions.validate


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
    init {
        validate(this) { person ->
            validate(PrivatePerson::type).isValid { it == PersonType.PRIVATE }
            validate(PrivatePerson::firstName).hasSize(min = 2, max = 4)
            validate(PrivatePerson::lastName).hasSize(min = 5, max = 10)
            validate(PrivatePerson::phoneNumber).withMessage("Phone or Email must be present") {
                (!it.isNullOrBlank() || !person.emails.isNullOrEmpty())
            }
            validate(PrivatePerson::phoneNumber).hasSize(max = 10)
            validate(PrivatePerson::address).validate {
                validate(Address::street).hasSize(min = 2)
                validate(Address::city).hasSize(min = 2)
                validate(Address::state).hasSize(min = 2)
                validate(Address::country).hasSize(min = 2)
                validate(Address::postalCode).hasSize(min = 2)
            }
            validate(PrivatePerson::weight).validate {
                validate(Weight::value).withMessage("numeric value out of bounds (<3 digits>.<2 digits> expected)") { value ->
                    value?.let { checkNumericValueBounds(it, 3,2) } ?: true
                }
            }
            validate(PrivatePerson::height).validate {
                validate(Height::value).withMessage("numeric value out of bounds (<3 digits>.<2 digits> expected)") { value ->
                    value?.let { checkNumericValueBounds(it, 3,2) } ?: true
                }
            }
        }
    }

    data class Custom(val message: String) : Constraint {
        override val messageKey: String = "org.valiktor.constraints.Custom.message"

        override val messageParams: Map<String, *>
            get() = mapOf(this::message.name to this.message)
    }

    fun <E, T> Validator<E>.Property<T?>.withMessage(message: String, block: (T?) -> Boolean) =
        this.validate(Custom(message)) { block.invoke(it) }
}
