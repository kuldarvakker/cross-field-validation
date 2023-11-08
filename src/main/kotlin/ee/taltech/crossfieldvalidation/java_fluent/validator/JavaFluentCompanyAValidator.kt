package ee.taltech.crossfieldvalidation.java_fluent.validator

import br.com.fluentvalidator.AbstractValidator
import br.com.fluentvalidator.predicate.LocalDatePredicate.localDateAfterOrEqual
import br.com.fluentvalidator.predicate.LogicalPredicate.not
import br.com.fluentvalidator.predicate.ObjectPredicate.nullValue
import br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween
import ee.taltech.crossfieldvalidation.java_fluent.model.company_a.JavaFluentCompanyAAgencyForm
import java.time.LocalDate

class JavaFluentCompanyAValidator : AbstractValidator<JavaFluentCompanyAAgencyForm>() {

    override fun rules() {
        setPropertyOnContext("COMPANY_A")
        ruleFor(JavaFluentCompanyAAgencyForm::firstName)
            .must(stringSizeBetween(1, 128))
            .`when`(not(nullValue()))
            .withMessage("Size 1, 128")
            // .withFieldName()
        ruleFor(JavaFluentCompanyAAgencyForm::lastName)
            .must(stringSizeBetween(1, 128))
            .`when`(not(nullValue()))
            .withMessage("Size 1, 128")
        // .withFieldName()
        ruleFor(JavaFluentCompanyAAgencyForm::birthDate)
            .must(localDateAfterOrEqual(LocalDate.ofYearDay(2000, 1)))
            .`when`(not(nullValue()))
            .withMessage("birthDate must be equal or after 2000")
        // .withFieldName()
    }
}
