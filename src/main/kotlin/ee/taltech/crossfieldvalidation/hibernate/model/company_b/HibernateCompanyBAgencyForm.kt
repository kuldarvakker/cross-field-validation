package ee.taltech.crossfieldvalidation.hibernate.model.company_b

import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.attributes.Gender
import ee.taltech.crossfieldvalidation.hibernate.constraints.CheckEnumValues
import ee.taltech.crossfieldvalidation.hibernate.model.HibernateAgencyForm
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import jakarta.validation.constraints.Size

data class HibernateCompanyBAgencyForm(
    @field:Schema(allowableValues = ["COMPANY_B"])
    override val agency: Agency = Agency.COMPANY_B,
    @field:Size(min = 1, max = 50)
    override val firstName: String,
    @field:Size(min = 1, max = 50)
    override val lastName: String,
    @field:Schema(example = "MALE", allowableValues = ["MALE"])
    @field:CheckEnumValues(allowedValues = ["MALE"])
    override val gender: Gender,
    @field:Size(min = 1, max = 2)
    @field:Valid
    override val socialMedia: List<HibernateCompanyBSocialMedia>,
    @field:Valid
    override val height: HibernateCompanyBHeight
) : HibernateAgencyForm()
