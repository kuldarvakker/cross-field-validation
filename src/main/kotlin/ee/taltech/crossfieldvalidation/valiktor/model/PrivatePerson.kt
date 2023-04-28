package ee.taltech.crossfieldvalidation.valiktor.model

import ee.taltech.crossfieldvalidation.checkNumericValueBounds
import ee.taltech.crossfieldvalidation.valiktor.model.attributes.Address
import ee.taltech.crossfieldvalidation.valiktor.model.attributes.Height
import ee.taltech.crossfieldvalidation.valiktor.model.attributes.Weight
import org.valiktor.*
import org.valiktor.functions.hasSize
import org.valiktor.functions.isValid
import org.valiktor.functions.validate
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible


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
//                validate(Height::value).include(Height::unit).withMessage("custom message") { value, unit ->
//                    value?.let { checkNumericValueBounds(it, 3,2) && unit != null && unit == Units.CM } ?: true
//                }
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

    fun <E, T, A> Validator<E>.Property<T?>.include(property: KProperty1<E, A?>): Pair<Validator<E>.Property<T?>, Validator<E>.Property<A & Any>> {
        val a = Validator(this.obj).Property(this.obj, property)
        return Pair(this, a)
    }

    fun <E, T, A, B> Validator<E>.Property<T?>.include(property: KProperty1<E, A?>, property2: KProperty1<E, B?>): Triple<Validator<E>.Property<T?>, Validator<E>.Property<A & Any>, Validator<E>.Property<B & Any>> {
        val a = Validator(this.obj).Property(this.obj, property)
        val b = Validator(this.obj).Property(this.obj, property2)
        return Triple(this, a, b)
    }

    fun <E, T, A> Pair<Validator<E>.Property<T?>, Validator<E>.Property<A & Any>>.withMessage(message: String, block: (T?, A?) -> Boolean) {
        this.first.validate(Custom(message)) { block.invoke(it, this.second.property.get(this.second.obj)) }
    }

    fun <E, T, A, B> Triple<Validator<E>.Property<T?>, Validator<E>.Property<A & Any>, Validator<E>.Property<B & Any>>.withMessage(message: String, block: (T?, A?, B?) -> Boolean) {
        this.first.validate(Custom(message)) { block.invoke(it, this.second.property.get(this.second.obj), this.third.property.get(this.third.obj)) }
    }

    inline fun <reified T : Any, R> T.getPrivateProperty(name: String): R? =
        T::class
            .memberProperties
            .firstOrNull { it.name == name }
            ?.apply { isAccessible = true }
            ?.get(this) as? R
}
