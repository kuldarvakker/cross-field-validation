package ee.taltech.crossfieldvalidation.hibernate.model

import ee.taltech.crossfieldvalidation.hibernate.model.attributes.Address

data class Company(
    override val type: PersonType = PersonType.COMPANY,
    val name: String,
    val phoneNumber: String,
    val email: String,
    val address: Address
) : Person()
