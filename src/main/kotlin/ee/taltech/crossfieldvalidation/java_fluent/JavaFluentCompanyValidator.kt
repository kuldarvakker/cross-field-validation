package ee.taltech.crossfieldvalidation.java_fluent

import br.com.fluentvalidator.AbstractValidator
import br.com.fluentvalidator.predicate.ComparablePredicate.equalTo
import br.com.fluentvalidator.predicate.LogicalPredicate.not
import br.com.fluentvalidator.predicate.ObjectPredicate.nullValue
import br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.java_fluent.model.JavaFluentCompany

class JavaFluentCompanyValidator : AbstractValidator<JavaFluentCompany> {

    constructor() : super()

    override fun rules() {
        setPropertyOnContext("person")

        ruleFor(JavaFluentCompany::type)
            .must(equalTo(Agency.COMPANY))
            .`when`(not(nullValue()))
            .withMessage("person type must be ${Agency.COMPANY.name}")
            .withFieldName(JavaFluentCompany::type.name)
            .critical()

        ruleFor(JavaFluentCompany::name)
            .must(not(nullValue()))
            .withMessage("cannot be null")
            .must(stringSizeBetween(2, 4))
            .withMessage("size must be between 2 and 4")
            .withFieldName(JavaFluentCompany::name.name)
    }
}
