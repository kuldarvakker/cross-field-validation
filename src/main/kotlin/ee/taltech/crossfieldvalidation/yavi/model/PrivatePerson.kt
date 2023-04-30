package ee.taltech.crossfieldvalidation.yavi.model

import am.ik.yavi.builder.validator
import ee.taltech.crossfieldvalidation.checkNumericValueBounds
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

val privatePersonValidator = validator<PrivatePerson> {
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
