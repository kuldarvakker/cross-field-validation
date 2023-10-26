package ee.taltech.crossfieldvalidation.java_fluent.model

import ee.taltech.crossfieldvalidation.common.model.PersonType

data class Company(
    override val type: PersonType = PersonType.COMPANY,
    val name: String,
) : Person()
