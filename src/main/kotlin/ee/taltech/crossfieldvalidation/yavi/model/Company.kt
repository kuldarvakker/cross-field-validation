package ee.taltech.crossfieldvalidation.yavi.model

import ee.taltech.crossfieldvalidation.yavi.model.attributes.Address

data class Company(
    override val type: PersonType = PersonType.COMPANY,
    val name: String,
    val phoneNumber: String,
    val email: String,
    val address: Address
) : Person()
