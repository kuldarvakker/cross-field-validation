package ee.taltech.crossfieldvalidation.hibernate.model

data class Company(
    override val type: PersonType = PersonType.COMPANY,
    val name: String
) : Person()
