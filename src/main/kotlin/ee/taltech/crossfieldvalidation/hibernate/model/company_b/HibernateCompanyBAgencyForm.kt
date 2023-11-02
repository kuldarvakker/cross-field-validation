package ee.taltech.crossfieldvalidation.hibernate.model.company_b

import com.fasterxml.jackson.annotation.JsonIgnore
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.attributes.Gender
import ee.taltech.crossfieldvalidation.hibernate.model.HibernateAgencyForm
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import jakarta.validation.constraints.AssertTrue

data class HibernateCompanyBAgencyForm(
    @field:Schema(allowableValues = ["COMPANY_B"])
    override val agency: Agency = Agency.COMPANY_B,
    override val firstName: String,
    override val lastName: String,
    @field:Schema(example = "MALE", allowableValues = ["MALE"])
    override val gender: Gender,
    @field:Valid
    override val socialMedia: List<HibernateCompanyBSocialMedia>,
    override val height: HibernateCompanyBHeight
) : HibernateAgencyForm() {

    @JsonIgnore
    @AssertTrue(message = "phoneNumber or email must be present")
    fun isAtLeastPhoneOrEmailPresent(): Boolean = (!phoneNumber.isNullOrBlank() || !email.isNullOrBlank())

    @JsonIgnore
    @AssertTrue(message = "first and last name should be total of length 49")
    fun isFirstAndLastNameLengthLessThan50(): Boolean = ((firstName + lastName).length < 50)

}
