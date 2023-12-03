package ee.taltech.crossfieldvalidation.validoctor.model.company_a

import com.miquido.validoctor.Validoctor
import com.miquido.validoctor.definition.Rule
import com.miquido.validoctor.definition.Rules.equalTo
import com.miquido.validoctor.definition.Rules.stringLengthInRange
import com.miquido.validoctor.definition.Rules.stringMaxLength
import com.miquido.validoctor.definition.Rules.stringMinLength
import com.miquido.validoctor.definition.SimpleRule
import ee.taltech.crossfieldvalidation.checkLocalDateIsAfterOrEqualsTo
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.validoctor.model.ValidoctorAgencyForm
import io.swagger.v3.oas.annotations.media.Schema

import java.time.LocalDate

data class ValidoctorCompanyAAgencyForm(
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
) : ValidoctorAgencyForm()

val checkLocalDateIsAfterOrEqualsToFirstDayOfYear2000: SimpleRule<LocalDate> =
    SimpleRule("Date must be after or equals to 2000-01-01") { it == null || checkLocalDateIsAfterOrEqualsTo(it, LocalDate.ofYearDay(2000, 1)) }

val validoctorCompanyAAgencyFormValidator: Rule<ValidoctorCompanyAAgencyForm> = Validoctor.rulesFor(ValidoctorCompanyAAgencyForm::class.java)
    .field("agency", equalTo(Agency.COMPANY_A))
    .fields(listOf("firstName", "lastName"), stringMinLength(1), stringMaxLength(128))
    .field(ValidoctorCompanyAAgencyForm::birthDate.name, checkLocalDateIsAfterOrEqualsToFirstDayOfYear2000)
    .rule("phoneNumber or email must be present", "phoneNumber", {!it.phoneNumber.isNullOrBlank() || !it.email.isNullOrBlank()})
    .field("phoneNumber", stringLengthInRange(5, 35))
    .field("email", stringLengthInRange(1, 128))
    .build()
