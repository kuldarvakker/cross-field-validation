package ee.taltech.crossfieldvalidation.konform.model

data class Company(
    override val type: PersonType = PersonType.COMPANY,
    val name: String
) : Person()
