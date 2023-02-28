package ee.taltech.crossfieldvalidation.ktor.model

import ee.taltech.crossfieldvalidation.ktor.model.attributes.Address
import ee.taltech.crossfieldvalidation.ktor.model.attributes.Length
import ee.taltech.crossfieldvalidation.ktor.model.attributes.Weight

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


