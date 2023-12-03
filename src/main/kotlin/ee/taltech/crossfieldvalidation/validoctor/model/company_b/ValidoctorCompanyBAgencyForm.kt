package ee.taltech.crossfieldvalidation.validoctor.model.company_b

import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.attributes.Gender
import ee.taltech.crossfieldvalidation.validoctor.model.ValidoctorAgencyForm
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema

data class ValidoctorCompanyBAgencyForm(
    @field:Schema(allowableValues = ["COMPANY_B"])
    override val agency: Agency = Agency.COMPANY_B,
    @field:Schema(minLength = 1, maxLength = 50)
    override val firstName: String,
    @field:Schema(minLength = 1, maxLength = 50)
    override val lastName: String,
    @field:Schema(example = "MALE", allowableValues = ["MALE"])
    override val gender: Gender,
    @field:ArraySchema(minItems = 1, maxItems = 2)
    override val socialMedia: List<ValidoctorCompanyBSocialMedia>,
    override val height: ValidoctorCompanyBHeight,
) : ValidoctorAgencyForm()
