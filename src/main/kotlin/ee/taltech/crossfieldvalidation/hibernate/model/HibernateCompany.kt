package ee.taltech.crossfieldvalidation.hibernate.model

import ee.taltech.crossfieldvalidation.common.model.PersonType
import jakarta.validation.constraints.Size

data class HibernateCompany(
    override val type: PersonType = PersonType.COMPANY,
    @field:Size(min = 2, max = 4)
    val name: String
) : HibernatePerson()
