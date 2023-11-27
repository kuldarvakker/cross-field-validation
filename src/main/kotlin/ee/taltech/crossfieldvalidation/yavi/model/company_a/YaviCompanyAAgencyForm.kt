package ee.taltech.crossfieldvalidation.yavi.model.company_a

import am.ik.yavi.builder.ValidatorBuilder
import am.ik.yavi.core.ViolationMessage
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.yavi.length
import ee.taltech.crossfieldvalidation.yavi.model.YaviAgencyForm
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class YaviCompanyAAgencyForm(
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
) : YaviAgencyForm()

val yaviCompanyAAgencyFormValidator = ValidatorBuilder.of<YaviCompanyAAgencyForm>()
    .constraint(YaviCompanyAAgencyForm::firstName, "firstName") { c -> c.length(1, 128) }
    .constraint(YaviCompanyAAgencyForm::lastName, "lastName") { c -> c.length(1, 128)}
    .constraint(YaviCompanyAAgencyForm::phoneNumber, "phoneNumber") { c -> c.length(5, 35) }
    .constraint(YaviCompanyAAgencyForm::email, "email") { c -> c.length(1, 128) }
    .constraint(YaviCompanyAAgencyForm::birthDate, "birthDate") { c -> c.afterOrEqual { LocalDate.ofYearDay(2000,1) }}
    .constraintOnTarget(
        { form -> !form.phoneNumber.isNullOrEmpty() && !form.email.isNullOrEmpty() },
        "phoneNumber",
        ViolationMessage.of(
            "",
            "phoneNumber or email must be present"
        )
    ).build()
