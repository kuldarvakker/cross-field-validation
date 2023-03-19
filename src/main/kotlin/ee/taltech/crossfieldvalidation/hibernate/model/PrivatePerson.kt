package ee.taltech.crossfieldvalidation.hibernate.model

import ee.taltech.crossfieldvalidation.hibernate.model.attributes.Address
import ee.taltech.crossfieldvalidation.hibernate.model.attributes.Height
import ee.taltech.crossfieldvalidation.hibernate.model.attributes.Weight
import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.validation.annotation.Validated

@Validated
data class PrivatePerson(
    @field:NotNull
    override val type: PersonType = PersonType.PRIVATE,
    @field:Size(min = 2, max = 4)
    val firstName: String,
    @field:Size(min = 5, max = 10)
    val lastName: String,
    @field:Size(max = 10)
    val phoneNumber: String,
    @field:NotEmpty
    val emails: List<String>,
    @field:Valid
    val address: Address,
    @field:Valid
    val height: Height,
    @field:Valid
    val weight: Weight
) : Person()


