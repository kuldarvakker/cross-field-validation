package ee.taltech.crossfieldvalidation.yavi.model

import ee.taltech.crossfieldvalidation.yavi.model.attributes.Address
import ee.taltech.crossfieldvalidation.yavi.model.attributes.Length
import ee.taltech.crossfieldvalidation.yavi.model.attributes.Weight

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


