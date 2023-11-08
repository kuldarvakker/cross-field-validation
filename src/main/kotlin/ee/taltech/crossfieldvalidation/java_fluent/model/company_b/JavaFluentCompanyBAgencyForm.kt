package ee.taltech.crossfieldvalidation.java_fluent.model.company_b

import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.attributes.Gender
import ee.taltech.crossfieldvalidation.java_fluent.model.JavaFluentAgencyForm
import io.swagger.v3.oas.annotations.media.Schema

data class JavaFluentCompanyBAgencyForm(
    @field:Schema(allowableValues = ["COMPANY_B"])
    override val agency: Agency = Agency.COMPANY_B,
    override val firstName: String,
    override val lastName: String,
    @field:Schema(example = "MALE", allowableValues = ["MALE"])
    override val gender: Gender,
    override val socialMedia: List<JavaFluentCompanyBSocialMedia>?,
    override val height: JavaFluentCompanyBHeight?,
) : JavaFluentAgencyForm()
