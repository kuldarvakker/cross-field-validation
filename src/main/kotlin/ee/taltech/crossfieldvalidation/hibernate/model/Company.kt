package ee.taltech.crossfieldvalidation.hibernate.model

import com.fasterxml.jackson.annotation.JsonIgnore
import ee.taltech.crossfieldvalidation.hibernate.model.attributes.Address
import jakarta.validation.constraints.AssertTrue

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.validation.annotation.Validated

@Validated
data class Company(
    override val type: PersonType = PersonType.COMPANY,
    @field:Size(min = 1)
    val name: String,
    @field:NotNull
    @field:Size(max = 10)
    val phoneNumber: String,
    val emails: List<String>,
    @field:NotEmpty
    val addresses: List<Address>
) : Person() {

    @JsonIgnore
    @AssertTrue
    fun isNameContainingCompanyType(): Boolean {
        return name.contains(regex = "OÜ|AS".toRegex())
    }
}
