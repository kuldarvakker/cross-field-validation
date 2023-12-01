package ee.taltech.crossfieldvalidation.kalidation.model.company_b

import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.attributes.Gender
import ee.taltech.crossfieldvalidation.kalidation.model.KalidationAgencyForm
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema

data class KalidationCompanyBAgencyForm(
    @field:Schema(allowableValues = ["COMPANY_B"])
    override val agency: Agency = Agency.COMPANY_B,
    @field:Schema(minLength = 1, maxLength = 50)
    override val firstName: String,
    @field:Schema(minLength = 1, maxLength = 50)
    override val lastName: String,
    @field:Schema(example = "MALE", allowableValues = ["MALE"])
    override val gender: Gender,
    @field:ArraySchema(minItems = 1, maxItems = 2)
    override val socialMedia: List<KalidationCompanyBSocialMedia>,
    override val height: KalidationCompanyBHeight,
) : KalidationAgencyForm()
