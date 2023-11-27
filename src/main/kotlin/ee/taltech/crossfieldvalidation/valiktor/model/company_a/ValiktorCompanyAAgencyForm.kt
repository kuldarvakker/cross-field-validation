package ee.taltech.crossfieldvalidation.valiktor.model.company_a

import ee.taltech.crossfieldvalidation.checkLocalDateIsAfterOrEqualsTo
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.valiktor.model.ValiktorAgencyForm
import ee.taltech.crossfieldvalidation.valiktor.withMessage
import io.swagger.v3.oas.annotations.media.Schema
import org.valiktor.functions.hasSize
import org.valiktor.functions.isValid
import org.valiktor.validate
import java.time.LocalDate

data class ValiktorCompanyAAgencyForm(
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
) : ValiktorAgencyForm()  {
    init {
        validate(this) { form ->
            validate(ValiktorCompanyAAgencyForm::agency).isValid { it == Agency.COMPANY_A }
            validate(ValiktorCompanyAAgencyForm::firstName).hasSize(min = 1, max = 128)
            validate(ValiktorCompanyAAgencyForm::lastName).hasSize(min = 1, max = 128)

            val reqDate = LocalDate.ofYearDay(2000, 1)
            validate(ValiktorCompanyAAgencyForm::birthDate).withMessage("Date must be after or equals to ${reqDate.year}-${reqDate.monthValue}-${reqDate.dayOfMonth}") {
                checkLocalDateIsAfterOrEqualsTo(form.birthDate, reqDate)
            }

            validate(ValiktorCompanyAAgencyForm::phoneNumber).hasSize(min = 5, max = 35)
            validate(ValiktorCompanyAAgencyForm::email).hasSize(min = 1, max = 128)
            validate(ValiktorCompanyAAgencyForm::phoneNumber).withMessage("phoneNumber or email must be present") {
                (!form.phoneNumber.isNullOrBlank() || !form.email.isNullOrBlank())
            }
        }
    }
}
