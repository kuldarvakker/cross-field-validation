package ee.taltech.crossfieldvalidation.java_fluent.validator

import br.com.fluentvalidator.AbstractValidator
import br.com.fluentvalidator.predicate.LocalDatePredicate.localDateAfterOrEqual
import br.com.fluentvalidator.predicate.LogicalPredicate.not
import br.com.fluentvalidator.predicate.ObjectPredicate.nullValue
import br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween
import ee.taltech.crossfieldvalidation.java_fluent.model.general.JavaFluentGeneralAgencyForm
import java.time.LocalDate

class JavaFluentGeneralValidator : AbstractValidator<JavaFluentGeneralAgencyForm>() {

    override fun rules() {
        setPropertyOnContext("GENERAL")
        ruleFor(JavaFluentGeneralAgencyForm::firstName)
            .must(stringSizeBetween(1, 128))
            .`when`(not(nullValue()))
            .withMessage("Size 1, 128")
            // .withFieldName()
        ruleFor(JavaFluentGeneralAgencyForm::lastName)
            .must(stringSizeBetween(1, 128))
            .`when`(not(nullValue()))
            .withMessage("Size 1, 128")
        // .withFieldName()
        ruleFor(JavaFluentGeneralAgencyForm::birthDate)
            .must(localDateAfterOrEqual(LocalDate.ofYearDay(2000, 1)))
            .`when`(not(nullValue()))
            .withMessage("birthDate must be equal or after 2000")
        // .withFieldName()
    }
}
