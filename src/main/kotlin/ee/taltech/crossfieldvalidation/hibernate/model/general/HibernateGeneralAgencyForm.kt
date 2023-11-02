package ee.taltech.crossfieldvalidation.hibernate.model.general

import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.attributes.Gender
import ee.taltech.crossfieldvalidation.common.model.attributes.Photo
import ee.taltech.crossfieldvalidation.hibernate.model.HibernateAgencyForm
import io.swagger.v3.oas.annotations.media.Schema
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
    override val phoneNumber: String,
    override val email: String,
    override val socialMedia: List<HibernateGeneralSocialMedia>,
    @field:Schema(description = "Candidate's current city, country")
    override val currentLocation: String,
    override val height: HibernateGeneralHeight,
    override val weight: HibernateGeneralWeight,
    override val shoeSize: Int,
    override val photos: List<Photo>
) : HibernateAgencyForm()
