package ee.taltech.crossfieldvalidation.hibernate.model

import jakarta.validation.constraints.Size

data class HibernateCompany(
    override val type: PersonType = PersonType.COMPANY,
    @field:Size(min = 2, max = 4)
    val name: String
) : Person()
