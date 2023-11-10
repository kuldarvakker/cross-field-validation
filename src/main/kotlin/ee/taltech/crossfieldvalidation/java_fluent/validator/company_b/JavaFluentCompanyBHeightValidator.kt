package ee.taltech.crossfieldvalidation.java_fluent.validator.company_b

import ee.taltech.crossfieldvalidation.checkNumericValueBounds
import ee.taltech.crossfieldvalidation.common.model.attributes.HeightUnits
import ee.taltech.crossfieldvalidation.java_fluent.model.company_b.JavaFluentCompanyBHeight
import ee.taltech.crossfieldvalidation.java_fluent.validator.FluentValidator
import java.util.function.Predicate.isEqual

class JavaFluentCompanyBHeightValidator(
    override val previousPath: String
) : FluentValidator<JavaFluentCompanyBHeight>(previousPath) {

    override fun rules() {
        setPropertyOnContext("height")

        fieldRuleOf(
            JavaFluentCompanyBHeight::unit,
            isEqual(HeightUnits.CM),
            "Allowed values are: [CM]"
        )
        fieldRuleOf(
            JavaFluentCompanyBHeight::value,
            checkNumericValueBounds(3, 0),
            "numeric value out of bounds (<3 digits>.<0 digits> expected)"
        )
    }
}