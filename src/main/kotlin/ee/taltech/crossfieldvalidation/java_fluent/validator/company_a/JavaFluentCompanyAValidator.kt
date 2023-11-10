package ee.taltech.crossfieldvalidation.java_fluent.validator.company_a

import br.com.fluentvalidator.predicate.LocalDatePredicate.localDateAfterOrEqual
import br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween
import ee.taltech.crossfieldvalidation.java_fluent.model.company_a.JavaFluentCompanyAAgencyForm
import ee.taltech.crossfieldvalidation.java_fluent.validator.FluentValidator
import java.time.LocalDate

class JavaFluentCompanyAValidator : FluentValidator<JavaFluentCompanyAAgencyForm>() {

    override fun rules() {
        setPropertyOnContext("COMPANY_A")

        fieldRuleOf(
            JavaFluentCompanyAAgencyForm::firstName,
            stringSizeBetween(1, 128),
            "size must be between 1 and 128"
        )
        fieldRuleOf(
            JavaFluentCompanyAAgencyForm::lastName,
            stringSizeBetween(1, 128),
            "size must be between 1 and 128"
        )
        fieldRuleOf(
            JavaFluentCompanyAAgencyForm::birthDate,
            localDateAfterOrEqual(LocalDate.ofYearDay(2000, 1)),
            "Date must be after or equals to 2000-1-1"
        )
        fieldRuleOf(
            field = JavaFluentCompanyAAgencyForm::phoneNumber,
            predicate = stringSizeBetween(5, 35),
            errorMessage = "size must be between 5 and 35"
        )

        entityRuleOf(
            fieldName = JavaFluentCompanyAAgencyForm::phoneNumber.name,
            predicate = {!it.phoneNumber.isNullOrBlank() || !it.email.isNullOrBlank()},
            message = "phoneNumber or email must be present"
        )
    }
}
