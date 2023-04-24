package ee.taltech.crossfieldvalidation.konform.model

import ee.taltech.crossfieldvalidation.konform.model.attributes.Address
import ee.taltech.crossfieldvalidation.konform.model.attributes.Height
import ee.taltech.crossfieldvalidation.konform.model.attributes.Weight

data class PrivatePerson(
    override val type: PersonType = PersonType.PRIVATE,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?,
    val email: String?,
    val address: Address,
    val height: Height,
    val weight: Weight
) : Person()

