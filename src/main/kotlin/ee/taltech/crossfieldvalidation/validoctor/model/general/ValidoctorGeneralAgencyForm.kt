package ee.taltech.crossfieldvalidation.validoctor.model.general

import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.attributes.Gender
import ee.taltech.crossfieldvalidation.validoctor.model.ValidoctorAgencyForm
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class ValidoctorGeneralAgencyForm(
    @field:Schema(allowableValues = ["GENERAL"])
    override val agency: Agency = Agency.GENERAL,
    @field:Schema(minLength = 1, maxLength = 50)
    override val firstName: String,
    @field:Schema(minLength = 1, maxLength = 50)
    override val lastName: String,
    @field:Schema(allowableValues = ["MALE", "FEMALE"])
    override val gender: Gender,
    override val birthDate: LocalDate,
    @field:Schema(minLength = 5, maxLength = 35)
    override val phoneNumber: String,
    @field:Schema(minLength = 1, maxLength = 128)
    override val email: String,
    @field:ArraySchema(minItems = 1, maxItems = 10)
    override val socialMedia: List<ValidoctorGeneralAgencySocialMedia>,
    override val height: ValidoctorGeneralAgencyHeight,
    override val weight: ValidoctorGeneralAgencyWeight
) : ValidoctorAgencyForm()
