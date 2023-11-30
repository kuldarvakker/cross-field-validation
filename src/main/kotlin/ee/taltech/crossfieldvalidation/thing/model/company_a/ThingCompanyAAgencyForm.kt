package ee.taltech.crossfieldvalidation.thing.model.company_a

import ee.taltech.crossfieldvalidation.checkLocalDateIsAfterOrEqualsTo
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.thing.model.ThingAgencyForm
import io.swagger.v3.oas.annotations.media.Schema
import so.kciter.thing.Rule
import so.kciter.thing.Thing
import so.kciter.thing.validator.ValidationRuleBuilder
import so.kciter.thing.validator.equal
import so.kciter.thing.validator.maxLength
import so.kciter.thing.validator.minLength
import java.time.LocalDate

data class ThingCompanyAAgencyForm(
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
) : ThingAgencyForm(), Thing<ThingCompanyAAgencyForm> {
    override val rule: Rule<ThingCompanyAAgencyForm>
        get() = Rule {
            Validation {
                ThingCompanyAAgencyForm::agency { equal(Agency.COMPANY_A) }
                ThingCompanyAAgencyForm::firstName { length(1, 128) }
                ThingCompanyAAgencyForm::lastName { length(1,128) }
                ThingCompanyAAgencyForm::birthDate {
                    addValidator("Date must be after or equals to 2000-01-01") {
                        checkLocalDateIsAfterOrEqualsTo(it, LocalDate.ofYearDay(2000, 1))
                    }
                }
                ThingCompanyAAgencyForm::phoneNumber.ifPresent { length(5, 35) }
                ThingCompanyAAgencyForm::email.ifPresent { length(1, 128) }
                ThingCompanyAAgencyForm::phoneNumber {  }

                addValidator("phoneNumber or email must be present") {
                    (!it.phoneNumber.isNullOrBlank() || !it.email.isNullOrBlank())
                }
            }
        }

    private fun ValidationRuleBuilder<String>.length(min: Int, max: Int) {
        minLength(min)
        maxLength(max)
    }
}
