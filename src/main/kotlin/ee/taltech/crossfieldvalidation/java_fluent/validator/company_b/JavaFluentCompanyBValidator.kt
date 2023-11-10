package ee.taltech.crossfieldvalidation.java_fluent.validator.company_b

import br.com.fluentvalidator.predicate.CollectionPredicate.hasSizeBetweenInclusive
import br.com.fluentvalidator.predicate.ObjectPredicate.nullValue
import br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween
import ee.taltech.crossfieldvalidation.common.model.attributes.Gender
import ee.taltech.crossfieldvalidation.java_fluent.model.company_b.JavaFluentCompanyBAgencyForm
import ee.taltech.crossfieldvalidation.java_fluent.validator.FluentValidator
import java.util.function.Predicate.isEqual
import java.util.function.Predicate.not

class JavaFluentCompanyBValidator : FluentValidator<JavaFluentCompanyBAgencyForm>() {

    override fun rules() {
        setPropertyOnContext("COMPANY_B")

        fieldRuleOf(
            JavaFluentCompanyBAgencyForm::firstName,
            stringSizeBetween(1, 50),
            "size must be between 1 and 50"
        )
        fieldRuleOf(
            JavaFluentCompanyBAgencyForm::lastName,
            stringSizeBetween(1, 50),
            "size must be between 1 and 50"
        )
        fieldRuleOf(
            JavaFluentCompanyBAgencyForm::gender,
            isEqual(Gender.MALE),
            "allowed values are: [MALE]"
        )
        fieldRuleOf(
            JavaFluentCompanyBAgencyForm::socialMedia,
            hasSizeBetweenInclusive(1,2),
            "size must be between 1 and 2"
        )

        ruleFor(JavaFluentCompanyBAgencyForm::height)
            .whenever(not(nullValue()))
            .withValidator(JavaFluentCompanyBHeightValidator("height"))

        ruleForEach { it.socialMedia }
            .whenever(not(nullValue()))
            .withValidator(JavaFluentCompanyBSocialMediaValidator(previousPath = "socialMedia", countable = true))
    }
}
