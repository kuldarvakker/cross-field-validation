package ee.taltech.crossfieldvalidation.hibernate.model

import com.fasterxml.jackson.annotation.JsonIgnore
import ee.taltech.crossfieldvalidation.common.model.PersonType
import ee.taltech.crossfieldvalidation.hibernate.model.attributes.HibernateAddress
import ee.taltech.crossfieldvalidation.hibernate.model.attributes.HibernateHeight
import ee.taltech.crossfieldvalidation.hibernate.model.attributes.HibernateWeight
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid
import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class HibernatePrivatePerson(
    @field:Schema(allowableValues = ["PRIVATE"])
    @field:NotNull
    override val type: PersonType = PersonType.PRIVATE,
    @field:Schema(example = "Mari")
    @field:Size(min = 2, max = 4)
    val firstName: String,
    @field:Schema(example = "Maasikas")
    @field:Size(min = 5, max = 10)
    val lastName: String,
    @field:Schema(example = "+37254541010")
    @field:Size(max = 10)
    val phoneNumber: String?,
    @field:Schema(example = """["email@taltech.ee", "email2@taltech.ee"]""")
    val emails: List<@Size(max = 128) String>?,
    @field:Schema(description = "Person's address")
    @field:Valid
    val address: HibernateAddress,
    @field:Schema(description = "Person's height")
    @field:Valid
    val height: HibernateHeight,
    @field:Schema(description = "Person's weight")
    @field:Valid
    val weight: HibernateWeight
) : HibernatePerson() {

    @JsonIgnore
    @AssertTrue(message = "Phone or Email must be present")
    fun isAtLeastPhoneOrEmailPresent(): Boolean = (!phoneNumber.isNullOrBlank() || !emails.isNullOrEmpty())
}
