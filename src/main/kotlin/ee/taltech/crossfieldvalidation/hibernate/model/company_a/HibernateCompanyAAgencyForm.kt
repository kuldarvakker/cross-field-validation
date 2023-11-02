package ee.taltech.crossfieldvalidation.hibernate.model.company_a

import com.fasterxml.jackson.annotation.JsonIgnore
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.hibernate.model.HibernateAgencyForm
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class HibernateCompanyAAgencyForm(
    @field:Schema(allowableValues = ["COMPANY_A"])
    override val agency: Agency = Agency.COMPANY_A,
    @field:Size(min = 1, max = 128)
    override val firstName: String,
    @field:Size(min = 1, max = 128)
    override val lastName: String,
    @field:Schema(description = "birthDate must be equal or after 2000")
    override val birthDate: LocalDate,
    @field:Size(min = 5, max = 35)
    @field:Schema(description = "phoneNumber or email must be present", example = "+372123456789")
    override val phoneNumber: String?,
    @field:Email
    @field:NotBlank
    @field:Size(max = 128)
    @field:Schema(description = "phoneNumber or email must be present", example = "email@email.ee")
    override val email: String?,
) : HibernateAgencyForm() {

    @JsonIgnore
    @AssertTrue(message = "phoneNumber or email must be present")
    fun isAtLeastPhoneOrEmailPresent(): Boolean = (!phoneNumber.isNullOrBlank() || !email.isNullOrBlank())

    @JsonIgnore
    @AssertTrue(message = "birthDate must be equal or after 2000")
    fun isBirthDateAfter2000(): Boolean {
        val date2000 = LocalDate.ofYearDay(2000, 1)
        return birthDate.isAfter(date2000) || birthDate.isEqual(date2000)
    }

}
