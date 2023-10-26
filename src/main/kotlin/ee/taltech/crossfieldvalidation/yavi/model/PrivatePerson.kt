package ee.taltech.crossfieldvalidation.yavi.model

import am.ik.yavi.builder.ValidatorBuilder
import am.ik.yavi.builder.validator
import am.ik.yavi.core.ViolationMessage
import ee.taltech.crossfieldvalidation.checkNumericValueBounds
import ee.taltech.crossfieldvalidation.common.model.PersonType
import ee.taltech.crossfieldvalidation.yavi.model.attributes.Address
import ee.taltech.crossfieldvalidation.yavi.model.attributes.Height
import ee.taltech.crossfieldvalidation.yavi.model.attributes.Weight

data class PrivatePerson(
    override val type: PersonType = PersonType.PRIVATE,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?,
    val emails: List<String>?,
    val address: Address,
    val height: Height,
    val weight: Weight
) : Person()

private val addressValidator = ValidatorBuilder.of<Address>()
    .constraint(Address::city, "city") { c -> c.notNull().greaterThanOrEqual(2) }
    .constraint(Address::state, "state") { c -> c.notNull().greaterThanOrEqual(2) }
    .constraint(Address::street, "street") { c -> c.notNull().greaterThanOrEqual(2) }
    .constraint(Address::country, "country") { c -> c.notNull().greaterThanOrEqual(2) }
    .constraint(Address::postalCode, "postalCode") { c -> c.notNull().greaterThanOrEqual(2) }
    .build()

private val heightValidator = ValidatorBuilder.of<Height>()
    .constraint(Height::value, "value") { c ->
        c.predicate(
            { checkNumericValueBounds(it, 3, 2) },
            "",
            "numeric value out of bounds (<3 digits>.<2 digits> expected)"
        )
    }
    .build()

private val weightValidator = ValidatorBuilder.of<Weight>()
    .constraint(Weight::value, "value") { c ->
        c.predicate(
            { checkNumericValueBounds(it, 3, 2) },
            "",
            "numeric value out of bounds (<3 digits>.<2 digits> expected)"
        )
    }
    .build()

val privatePersonValidator = ValidatorBuilder.of<PrivatePerson>()
    .constraint(PrivatePerson::firstName, "firstName") { c -> c.notNull().greaterThanOrEqual(2).lessThanOrEqual(4) }
    .constraint(PrivatePerson::lastName, "lastName") { c -> c.notNull().greaterThanOrEqual(5).lessThanOrEqual(10) }
    .constraint(PrivatePerson::phoneNumber, "phoneNumber") { c -> c.greaterThanOrEqual(1).lessThanOrEqual(10) }
    .constraintOnTarget(
        { person -> !person.phoneNumber.isNullOrEmpty() && !person.emails.isNullOrEmpty() },
        "atLeastPhoneOrEmailPresent",
        ViolationMessage.of(
            "",
            "Phone or Email must be present"
        )
    )
    .nest(PrivatePerson::address, "address", addressValidator)
    .nest(PrivatePerson::height, "height", heightValidator)
    .nest(PrivatePerson::weight, "weight", weightValidator)
    .build()

val privatePersonValidatorKt = validator<PrivatePerson> {
    PrivatePerson::firstName {
        notNull()
        greaterThanOrEqual(2)
        lessThanOrEqual(4)
    }
    PrivatePerson::lastName {
        notNull()
        greaterThanOrEqual(5)
        lessThanOrEqual(10)
    }
    PrivatePerson::phoneNumber {
        greaterThanOrEqual(1)
        lessThanOrEqual(10)
    }
    PrivatePerson::address nest {
        Address::city { greaterThanOrEqual(2) }
        Address::state { greaterThanOrEqual(2) }
        Address::street { greaterThanOrEqual(2) }
        Address::country { greaterThanOrEqual(2) }
        Address::postalCode { greaterThanOrEqual(2) }
    }
    PrivatePerson::height nest {
        Height::value {
            this.predicate(
                { checkNumericValueBounds(it, 3, 2) },
                "",
                "numeric value out of bounds (<3 digits>.<2 digits> expected)"
            )
        }
    }
    PrivatePerson::weight nest {
        Weight::value {
            this.predicate(
                { checkNumericValueBounds(it, 3, 2) },
                "",
                "numeric value out of bounds (<3 digits>.<2 digits> expected)"
            )
        }
    }
    // onCondition({ a, b -> a.phoneNumber.isNullOrEmpty() && a.emails.isNullOrEmpty() }) {}
    build()
}
