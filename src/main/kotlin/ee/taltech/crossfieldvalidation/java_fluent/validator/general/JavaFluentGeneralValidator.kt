package ee.taltech.crossfieldvalidation.java_fluent.validator.general

import br.com.fluentvalidator.predicate.LocalDatePredicate.localDateAfterOrEqual
import br.com.fluentvalidator.predicate.LogicalPredicate.not
import br.com.fluentvalidator.predicate.ObjectPredicate.nullValue
import br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween
import ee.taltech.crossfieldvalidation.java_fluent.model.general.JavaFluentGeneralAgencyForm
import ee.taltech.crossfieldvalidation.java_fluent.validator.FluentValidator
import java.time.LocalDate

class JavaFluentGeneralValidator : FluentValidator<JavaFluentGeneralAgencyForm>() {

    override fun rules() {
        setPropertyOnContext("GENERAL")
        TODO("finish this validation")
    }
}
