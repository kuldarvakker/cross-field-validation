package ee.taltech.crossfieldvalidation.java_fluent.model

import ee.taltech.crossfieldvalidation.common.model.PersonType
import ee.taltech.crossfieldvalidation.java_fluent.model.attributes.JavaFluentAddress
import ee.taltech.crossfieldvalidation.java_fluent.model.attributes.JavaFluentHeight
import ee.taltech.crossfieldvalidation.java_fluent.model.attributes.JavaFluentWeight

data class JavaFluentPrivatePerson(
    override val type: PersonType = PersonType.PRIVATE,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String?,
    val emails: List<String>?,
    val address: JavaFluentAddress,
    val height: JavaFluentHeight,
    val weight: JavaFluentWeight
) : JavaFluentPerson()
