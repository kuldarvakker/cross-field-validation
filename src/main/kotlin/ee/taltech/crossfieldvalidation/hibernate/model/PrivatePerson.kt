package ee.taltech.crossfieldvalidation.hibernate.model

import ee.taltech.crossfieldvalidation.hibernate.model.attributes.Address
import ee.taltech.crossfieldvalidation.hibernate.model.attributes.Height
import ee.taltech.crossfieldvalidation.hibernate.model.attributes.Weight
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.validation.annotation.Validated

@Validated
data class PrivatePerson(
    @NotNull
    override val type: PersonType = PersonType.PRIVATE,
    @Size(min = 2, max = 4)
    val firstName: String,
    @Size(min = 5, max = 10)
    val lastName: String,
    @Size(max = 10)
    val phoneNumber: String?,
    @NotEmpty
    val emails: List<@Email String>,
    @Valid
    val address: Address,
    @Valid
    val height: Height,
    val weight: Weight
) : Person()


