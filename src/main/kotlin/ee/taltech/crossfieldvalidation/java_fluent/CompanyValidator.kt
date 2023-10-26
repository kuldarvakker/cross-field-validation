package ee.taltech.crossfieldvalidation.java_fluent

import br.com.fluentvalidator.AbstractValidator
import br.com.fluentvalidator.predicate.ComparablePredicate.equalTo
import br.com.fluentvalidator.predicate.LogicalPredicate.not
import br.com.fluentvalidator.predicate.ObjectPredicate.nullValue
import br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween
import ee.taltech.crossfieldvalidation.common.model.PersonType
import ee.taltech.crossfieldvalidation.java_fluent.model.Company

class CompanyValidator : AbstractValidator<Company> {

    constructor() : super()

    override fun rules() {
        setPropertyOnContext("person")

        ruleFor(Company::type)
            .must(equalTo(PersonType.COMPANY))
            .`when`(not(nullValue()))
            .withMessage("person type must be ${PersonType.COMPANY.name}")
            .withFieldName(Company::type.name)
            .critical()

        ruleFor(Company::name)
            .must(not(nullValue()))
            .withMessage("cannot be null")
            .must(stringSizeBetween(2, 4))
            .withMessage("size must be between 2 and 4")
            .withFieldName(Company::name.name)
    }
}
