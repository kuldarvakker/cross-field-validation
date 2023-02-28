package ee.taltech.crossfieldvalidation.valiktor.model

import ee.taltech.crossfieldvalidation.valiktor.model.attributes.Address

data class Company(
    override val type: PersonType = PersonType.COMPANY,
    val name: String,
    val phoneNumber: String,
    val email: String,
    val address: Address
) : Person()
