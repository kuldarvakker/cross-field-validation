package ee.taltech.crossfieldvalidation.yavi.model

data class Company(
    override val type: PersonType = PersonType.COMPANY,
    val name: String,
) : Person()
