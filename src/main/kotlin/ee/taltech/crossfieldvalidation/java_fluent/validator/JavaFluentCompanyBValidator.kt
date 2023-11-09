package ee.taltech.crossfieldvalidation.java_fluent.validator

import br.com.fluentvalidator.predicate.LocalDatePredicate.localDateAfterOrEqual
import br.com.fluentvalidator.predicate.LogicalPredicate.not
import br.com.fluentvalidator.predicate.ObjectPredicate.nullValue
import br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween
import ee.taltech.crossfieldvalidation.java_fluent.model.company_b.JavaFluentCompanyBAgencyForm
import java.time.LocalDate

class JavaFluentCompanyBValidator : FluentValidator<JavaFluentCompanyBAgencyForm>() {

    override fun rules() {
        setPropertyOnContext("COMPANY_B")

        ruleFor(JavaFluentCompanyBAgencyForm::firstName)
            .must(stringSizeBetween(1, 128))
            .`when`(not(nullValue()))
            .withMessage("Size 1, 128")
            // .withFieldName()
        ruleFor(JavaFluentCompanyBAgencyForm::lastName)
            .must(stringSizeBetween(1, 128))
            .`when`(not(nullValue()))
            .withMessage("Size 1, 128")
        // .withFieldName()
        ruleFor(JavaFluentCompanyBAgencyForm::birthDate)
            .must(localDateAfterOrEqual(LocalDate.ofYearDay(2000, 1)))
            .`when`(not(nullValue()))
            .withMessage("birthDate must be equal or after 2000")
        // .withFieldName()
    }
}
