package ee.taltech.crossfieldvalidation.yavi.model

import am.ik.yavi.builder.validator
import ee.taltech.crossfieldvalidation.common.model.PersonType

data class YaviCompany(
    override val type: PersonType = PersonType.COMPANY,
    val name: String,
) : YaviPerson()

val yaviCompanyValidator = validator<YaviCompany> {
    YaviCompany::name {
        notNull()
        greaterThanOrEqual(2)
        lessThanOrEqual(4)
    }.build()
}
