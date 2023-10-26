package ee.taltech.crossfieldvalidation.java_fluent.model

import ee.taltech.crossfieldvalidation.common.model.PersonType
import ee.taltech.crossfieldvalidation.java_fluent.model.attributes.Address
import ee.taltech.crossfieldvalidation.java_fluent.model.attributes.Height
import ee.taltech.crossfieldvalidation.java_fluent.model.attributes.Weight

data class PrivatePerson(
    override val type: PersonType = PersonType.PRIVATE,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?,
    val emails: List<String>?,
    val address: Address,
    val height: Height,
    val weight: Weight
) : Person()
