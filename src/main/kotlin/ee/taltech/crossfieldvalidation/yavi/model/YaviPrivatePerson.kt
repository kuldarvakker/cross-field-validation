package ee.taltech.crossfieldvalidation.yavi.model

import am.ik.yavi.builder.ValidatorBuilder
import am.ik.yavi.builder.validator
import am.ik.yavi.core.ViolationMessage
import ee.taltech.crossfieldvalidation.checkNumericValueBounds
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.yavi.model.attributes.YaviAddress
import ee.taltech.crossfieldvalidation.yavi.model.attributes.YaviHeight
import ee.taltech.crossfieldvalidation.yavi.model.attributes.YaviWeight
import io.swagger.v3.oas.annotations.media.Schema

data class YaviPrivatePerson(
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
    val address: YaviAddress,
    @field:Schema(description = "Person's height")
    val height: YaviHeight,
    @field:Schema(description = "Person's weight")
    val weight: YaviWeight
) : YaviPerson()

private val addressValidator = ValidatorBuilder.of<YaviAddress>()
    .constraint(YaviAddress::city, "city") { c -> c.notNull().greaterThanOrEqual(2) }
    .constraint(YaviAddress::state, "state") { c -> c.notNull().greaterThanOrEqual(2) }
    .constraint(YaviAddress::street, "street") { c -> c.notNull().greaterThanOrEqual(2) }
    .constraint(YaviAddress::country, "country") { c -> c.notNull().greaterThanOrEqual(2) }
    .constraint(YaviAddress::postalCode, "postalCode") { c -> c.notNull().greaterThanOrEqual(2) }
    .build()

private val heightValidator = ValidatorBuilder.of<YaviHeight>()
    .constraint(YaviHeight::value, "value") { c ->
        c.predicate(
            { checkNumericValueBounds(it, 3, 2) },
            "",
            "numeric value out of bounds (<3 digits>.<2 digits> expected)"
        )
    }
    .build()

private val weightValidator = ValidatorBuilder.of<YaviWeight>()
    .constraint(YaviWeight::value, "value") { c ->
        c.predicate(
            { checkNumericValueBounds(it, 3, 2) },
            "",
            "numeric value out of bounds (<3 digits>.<2 digits> expected)"
        )
    }
    .build()

val yaviPrivatePersonValidator = ValidatorBuilder.of<YaviPrivatePerson>()
    .constraint(YaviPrivatePerson::firstName, "firstName") { c -> c.notNull().greaterThanOrEqual(2).lessThanOrEqual(4) }
    .constraint(YaviPrivatePerson::lastName, "lastName") { c -> c.notNull().greaterThanOrEqual(5).lessThanOrEqual(10) }
    .constraint(YaviPrivatePerson::phoneNumber, "phoneNumber") { c -> c.greaterThanOrEqual(1).lessThanOrEqual(10) }
    .constraintOnTarget(
        { person -> !person.phoneNumber.isNullOrEmpty() && !person.emails.isNullOrEmpty() },
        "atLeastPhoneOrEmailPresent",
        ViolationMessage.of(
            "",
            "Phone or Email must be present"
        )
    )
    .nest(YaviPrivatePerson::address, "address", addressValidator)
    .nest(YaviPrivatePerson::height, "height", heightValidator)
    .nest(YaviPrivatePerson::weight, "weight", weightValidator)
    .build()

val yaviPrivatePersonValidatorKt = validator<YaviPrivatePerson> {
    YaviPrivatePerson::firstName {
        notNull()
        greaterThanOrEqual(2)
        lessThanOrEqual(4)
    }
    YaviPrivatePerson::lastName {
        notNull()
        greaterThanOrEqual(5)
        lessThanOrEqual(10)
    }
    YaviPrivatePerson::phoneNumber {
        greaterThanOrEqual(1)
        lessThanOrEqual(10)
    }
    YaviPrivatePerson::address nest {
        YaviAddress::city { greaterThanOrEqual(2) }
        YaviAddress::state { greaterThanOrEqual(2) }
        YaviAddress::street { greaterThanOrEqual(2) }
        YaviAddress::country { greaterThanOrEqual(2) }
        YaviAddress::postalCode { greaterThanOrEqual(2) }
    }
    YaviPrivatePerson::height nest {
        YaviHeight::value {
            this.predicate(
                { checkNumericValueBounds(it, 3, 2) },
                "",
                "numeric value out of bounds (<3 digits>.<2 digits> expected)"
            )
        }
    }
    YaviPrivatePerson::weight nest {
        YaviWeight::value {
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
