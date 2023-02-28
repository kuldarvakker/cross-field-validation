package ee.taltech.crossfieldvalidation.java_fluent.model

import ee.taltech.crossfieldvalidation.java_fluent.model.attributes.Address
import ee.taltech.crossfieldvalidation.java_fluent.model.attributes.Length
import ee.taltech.crossfieldvalidation.java_fluent.model.attributes.Weight

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


