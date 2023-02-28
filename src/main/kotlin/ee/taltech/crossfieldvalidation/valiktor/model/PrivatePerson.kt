package ee.taltech.crossfieldvalidation.valiktor.model

import ee.taltech.crossfieldvalidation.valiktor.model.attributes.Address
import ee.taltech.crossfieldvalidation.valiktor.model.attributes.Length
import ee.taltech.crossfieldvalidation.valiktor.model.attributes.Weight

data class PrivatePerson(
    override val type: PersonType = PersonType.PRIVATE,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?,
    val email: String?,
    val address: Address,
    val height: Length,
    val weight: Weight
) : Person()


