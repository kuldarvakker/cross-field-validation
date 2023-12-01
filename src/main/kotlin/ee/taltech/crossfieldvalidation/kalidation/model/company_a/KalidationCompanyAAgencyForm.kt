package ee.taltech.crossfieldvalidation.kalidation.model.company_a

import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.kalidation.model.KalidationAgencyForm
import io.swagger.v3.oas.annotations.media.Schema

import java.time.LocalDate

data class KalidationCompanyAAgencyForm(
    @field:Schema(allowableValues = ["COMPANY_A"])
    override val agency: Agency = Agency.COMPANY_A,
    @field:Schema(minLength = 1, maxLength = 128)
    override val firstName: String,
    @field:Schema(minLength = 1, maxLength = 128)
    override val lastName: String,
    @field:Schema(description = "birthDate must be equal or after 2000")
    override val birthDate: LocalDate,
    @field:Schema(minLength = 5, maxLength = 35, description = "phoneNumber or email must be present", example = "+372123456789")
    override val phoneNumber: String?,
    @field:Schema(minLength = 1, maxLength = 128, description = "phoneNumber or email must be present", example = "email@email.ee")
    override val email: String?,
) : KalidationAgencyForm()
