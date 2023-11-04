package ee.taltech.crossfieldvalidation.java_fluent

import br.com.fluentvalidator.AbstractValidator
import br.com.fluentvalidator.predicate.ComparablePredicate.equalTo
import br.com.fluentvalidator.predicate.LogicalPredicate.not
import br.com.fluentvalidator.predicate.ObjectPredicate.nullValue
import br.com.fluentvalidator.predicate.StringPredicate.stringSizeGreaterThanOrEqual
import br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.java_fluent.model.JavaFluentPrivatePerson

class JavaFluentPrivatePersonValidator : AbstractValidator<JavaFluentPrivatePerson> {

    constructor() : super()

    override fun rules() {
        setPropertyOnContext("person")

        ruleFor(JavaFluentPrivatePerson::type)
            .must(equalTo(Agency.GENERAL))
            .`when`(not(nullValue()))
            .withMessage("person type must be PRIVATE")
            .withFieldName(JavaFluentPrivatePerson::type.name)
            .critical()

        ruleFor(JavaFluentPrivatePerson::firstName)
            .must(not(nullValue()))
            .withMessage("first name cannot be null")
            .withFieldName("firstName")
            .must(stringSizeBetween(2,4))
            .withMessage("size must be between 2 and 4")
            .withFieldName("firstName")

        ruleFor(JavaFluentPrivatePerson::lastName)
            .must(not(nullValue()))
            .withMessage("last name cannot be null")
            .withFieldName("lastName")
            .must(stringSizeBetween(5,10))
            .withMessage("lastName is not between size 5 and 10")

        ruleFor(JavaFluentPrivatePerson::phoneNumber)
            .must(stringSizeBetween(1,10))
            .`when`(not(nullValue()))
            .withMessage("phoneNumber can be max length of 10")

        ruleFor(JavaFluentPrivatePerson::address)
            .must(not(nullValue()))
            .withMessage("address cannot be null")

        validateAddress()

        ruleFor {person -> person.height.value}
            .must {it.scale() <= 2}
            .withMessage("numeric value out of bounds (<3 digits>.<2 digits> expected)")
            .withFieldName("height.value")
            .must {it.precision() - it.scale() <= 3}
            .withMessage("numeric value out of bounds (<3 digits>.<2 digits> expected)")
            .withFieldName("height.value")

        ruleFor {person -> person.weight.value}
            .must {it.scale() <= 2}
            .withMessage("numeric value out of bounds (<3 digits>.<2 digits> expected)")
            .withFieldName("weight.value")
            .must {it.precision() - it.scale() <= 3}
            .withMessage("numeric value out of bounds (<3 digits>.<2 digits> expected)")
            .withFieldName("weight.value")

        ruleFor {person -> person}
            .must {(!it.phoneNumber.isNullOrBlank() || !it.emails.isNullOrEmpty())}
            .withMessage("Phone or Email must be present")
            .withFieldName("atLeastPhoneOrEmailPresent")
    }

    private fun validateAddress() {
        ruleFor { person -> person.address.street }
            .must(stringSizeGreaterThanOrEqual(2))
            .withMessage("size must be at greater or equal to 2")
            .withFieldName("address.street")

        ruleFor { person -> person.address.city }
            .must(stringSizeGreaterThanOrEqual(2))
            .withMessage("size must be at greater or equal to 2")
            .withFieldName("address.city")

        ruleFor { person -> person.address.state }
            .must(stringSizeGreaterThanOrEqual(2))
            .withMessage("size must be at greater or equal to 2")
            .withFieldName("address.state")

        ruleFor { person -> person.address.country }
            .must(stringSizeGreaterThanOrEqual(2))
            .withMessage("size must be at greater or equal to 2")
            .withFieldName("address.country")

        ruleFor { person -> person.address.postalCode }
            .must(stringSizeGreaterThanOrEqual(2))
            .withMessage("size must be at greater or equal to 2")
            .withFieldName("address.postalCode")
    }
}
