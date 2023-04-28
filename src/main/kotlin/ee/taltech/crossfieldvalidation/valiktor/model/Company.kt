package ee.taltech.crossfieldvalidation.valiktor.model

data class Company(
    override val type: PersonType = PersonType.COMPANY,
    val name: String,
) : Person()
