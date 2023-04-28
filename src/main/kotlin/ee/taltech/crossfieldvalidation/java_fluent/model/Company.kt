package ee.taltech.crossfieldvalidation.java_fluent.model

data class Company(
    override val type: PersonType = PersonType.COMPANY,
    val name: String,
) : Person()
