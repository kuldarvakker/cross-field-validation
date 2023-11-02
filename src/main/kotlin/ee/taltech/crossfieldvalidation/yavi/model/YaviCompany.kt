package ee.taltech.crossfieldvalidation.yavi.model

import am.ik.yavi.builder.validator
import ee.taltech.crossfieldvalidation.common.model.ServiceProvider
import io.swagger.v3.oas.annotations.media.Schema

data class YaviCompany(
    @field:Schema(allowableValues = ["COMPANY"])
    override val type: ServiceProvider = ServiceProvider.COMPANY,
    @field:Schema(description = "Company's name", example = "Maja Üks OÜ", minLength = 2, maxLength = 4)
    val name: String,
) : YaviPerson()

val yaviCompanyValidator = validator<YaviCompany> {
    YaviCompany::name {
        notNull()
        greaterThanOrEqual(2)
        lessThanOrEqual(4)
    }.build()
}
