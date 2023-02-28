package ee.taltech.crossfieldvalidation.hibernate.model

import ee.taltech.crossfieldvalidation.hibernate.model.attributes.Address
import jakarta.validation.constraints.NotEmpty

data class Company(
    override val type: PersonType = PersonType.COMPANY,
    val name: String,
    val phoneNumber: String,
    val emails: List<String>,
    @NotEmpty
    val addresses: List<Address>
) : Person()
