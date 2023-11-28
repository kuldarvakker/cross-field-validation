package ee.taltech.crossfieldvalidation.akkurate.model.company_a

import dev.nesk.akkurate.Validator
import dev.nesk.akkurate.annotations.Validate
import dev.nesk.akkurate.constraints.builders.hasLengthBetween
import dev.nesk.akkurate.constraints.builders.isAfterOrEqualTo
import dev.nesk.akkurate.constraints.builders.isEqualTo
import dev.nesk.akkurate.constraints.constrain
import dev.nesk.akkurate.constraints.otherwise
import ee.taltech.crossfieldvalidation.akkurate.model.AkkurateAgencyForm
import ee.taltech.crossfieldvalidation.akkurate.model.validation.accessors.agency
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.validation.accessors.birthDate
import ee.taltech.crossfieldvalidation.common.model.validation.accessors.email
import ee.taltech.crossfieldvalidation.common.model.validation.accessors.firstName
import ee.taltech.crossfieldvalidation.common.model.validation.accessors.lastName
import ee.taltech.crossfieldvalidation.common.model.validation.accessors.phoneNumber
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

@Validate
data class AkkurateCompanyAAgencyForm(
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
) : AkkurateAgencyForm()

val validateAkkurateCompanyAAgencyForm = Validator<AkkurateCompanyAAgencyForm> {
    agency.isEqualTo(Agency.COMPANY_A)
    firstName.hasLengthBetween(1..128)
    lastName.hasLengthBetween(1..128)

    val reqDate = LocalDate.ofYearDay(2000, 1)
    birthDate.isAfterOrEqualTo(reqDate) otherwise {
        "Date must be after or equals to ${reqDate.year}-${reqDate.monthValue}-${reqDate.dayOfMonth}"
    }

    constrain {
        (!it.phoneNumber.isNullOrBlank() || !it.email.isNullOrBlank())
    } otherwise { "phoneNumber or email must be present" }

    if (email.unwrap() != null) {
        email.hasLengthBetween(1..128)
    }

    if (phoneNumber.unwrap() != null) {
        phoneNumber.hasLengthBetween(5..35)
    }
}

