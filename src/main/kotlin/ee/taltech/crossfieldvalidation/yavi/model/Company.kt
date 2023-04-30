package ee.taltech.crossfieldvalidation.yavi.model

import am.ik.yavi.builder.validator

data class Company(
    override val type: PersonType = PersonType.COMPANY,
    val name: String,
) : Person()

val companyValidator = validator<Company> {
    Company::name {
        notNull()
        greaterThanOrEqual(2)
        lessThanOrEqual(4)
    }.build()
}
