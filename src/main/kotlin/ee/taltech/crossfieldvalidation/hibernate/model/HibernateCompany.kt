package ee.taltech.crossfieldvalidation.hibernate.model

import ee.taltech.crossfieldvalidation.common.model.PersonType
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size

data class HibernateCompany(
    @field:Schema(allowableValues = ["COMPANY"])
    override val type: PersonType = PersonType.COMPANY,
    @field:Size(min = 2, max = 4)
    @field:Schema(description = "Company's name", example = "Maja Üks OÜ")
    val name: String
) : HibernatePerson()
