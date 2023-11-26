package ee.taltech.crossfieldvalidation.konform.model.company_a

import ee.taltech.crossfieldvalidation.checkLocalDateIsAfterOrEqualsTo
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.konform.length
import ee.taltech.crossfieldvalidation.konform.model.KonformAgencyForm
import io.konform.validation.Validation
import io.konform.validation.jsonschema.enum
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class KonformCompanyAAgencyForm(
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
) : KonformAgencyForm() {

    companion object {
        val validate = Validation<KonformCompanyAAgencyForm> {
            KonformCompanyAAgencyForm::agency { enum(Agency.COMPANY_A) }
            KonformCompanyAAgencyForm::firstName { length(1, 128) }
            KonformCompanyAAgencyForm::lastName { length(1, 128) }
            KonformCompanyAAgencyForm::birthDate {
                val reqDate = LocalDate.ofYearDay(2000, 1)
                addConstraint("Date must be after or equals to ${reqDate.year}-${reqDate.monthValue}-${reqDate.dayOfMonth}") {
                    checkLocalDateIsAfterOrEqualsTo(it, reqDate)
                }
            }
            KonformCompanyAAgencyForm::phoneNumber ifPresent { length(5, 35) }
            KonformCompanyAAgencyForm::email ifPresent { length(1, 128) }

            addConstraint("phoneNumber or email must be present") { person ->
                (!person.phoneNumber.isNullOrBlank() || !person.email.isNullOrBlank())
            }
        }
    }
}
