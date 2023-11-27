package ee.taltech.crossfieldvalidation.valiktor.model.general

import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.attributes.Gender
import ee.taltech.crossfieldvalidation.konform.length
import ee.taltech.crossfieldvalidation.valiktor.model.ValiktorAgencyForm
import io.konform.validation.Validation
import io.konform.validation.jsonschema.enum
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class ValiktorGeneralAgencyForm(
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
    override val socialMedia: List<ValiktorGeneralAgencySocialMedia>,
    override val height: ValiktorGeneralAgencyHeight,
    override val weight: ValiktorGeneralAgencyWeight
) : ValiktorAgencyForm() {
    companion object {
        val validate = Validation<ValiktorGeneralAgencyForm> {
            ValiktorGeneralAgencyForm::agency { enum(Agency.GENERAL) }
            ValiktorGeneralAgencyForm::firstName { length(1, 50) }
            ValiktorGeneralAgencyForm::lastName { length(1, 50) }
        }
    }
}
