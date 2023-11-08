package ee.taltech.crossfieldvalidation.hibernate.model.general

import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.attributes.Gender
import ee.taltech.crossfieldvalidation.hibernate.model.HibernateAgencyForm
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class HibernateGeneralAgencyForm(
    @field:Schema(allowableValues = ["GENERAL"])
    override val agency: Agency = Agency.GENERAL,
    @field:Size(min = 1, max = 50)
    override val firstName: String,
    @field:Size(min = 1, max = 50)
    override val lastName: String,
    @field:Schema(allowableValues = ["MALE", "FEMALE"])
    override val gender: Gender,
    override val birthDate: LocalDate,
    @field:Size(min = 5, max = 35)
    override val phoneNumber: String,
    @field:Email
    @field:Size(min = 1, max = 128)
    override val email: String,
    @field:Size(min = 1, max = 10)
    @field:Valid
    override val socialMedia: List<HibernateGeneralSocialMedia>,
    @field:Valid
    override val height: HibernateGeneralHeight,
    @field:Valid
    override val weight: HibernateGeneralWeight,
) : HibernateAgencyForm()
