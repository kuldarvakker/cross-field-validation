package ee.taltech.crossfieldvalidation.hibernate.model

import com.fasterxml.jackson.annotation.JsonIgnore
import ee.taltech.crossfieldvalidation.common.model.PersonType
import ee.taltech.crossfieldvalidation.hibernate.model.attributes.HibernateAddress
import ee.taltech.crossfieldvalidation.hibernate.model.attributes.HibernateHeight
import ee.taltech.crossfieldvalidation.hibernate.model.attributes.HibernateWeight
import jakarta.validation.Valid
import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class HibernatePrivatePerson(
    @field:NotNull
    override val type: PersonType = PersonType.PRIVATE,
    @field:Size(min = 2, max = 4)
    val firstName: String,
    @field:Size(min = 5, max = 10)
    val lastName: String,
    @field:Size(max = 10)
    val phoneNumber: String?,
    val emails: List<@Size(max = 1) String>?,
    @field:Valid
    val address: HibernateAddress,
    @field:Valid
    val height: HibernateHeight,
    @field:Valid
    val weight: HibernateWeight
) : HibernatePerson() {

    @JsonIgnore
    @AssertTrue(message = "Phone or Email must be present")
    fun isAtLeastPhoneOrEmailPresent(): Boolean = (!phoneNumber.isNullOrBlank() || !emails.isNullOrEmpty())
}
