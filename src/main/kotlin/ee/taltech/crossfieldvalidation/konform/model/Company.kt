package ee.taltech.crossfieldvalidation.konform.model

import ee.taltech.crossfieldvalidation.konform.model.attributes.Address

data class Company(
    override val type: PersonType = PersonType.COMPANY,
    val name: String,
    val phoneNumber: String,
    val email: String,
    val address: Address
) : Person()
