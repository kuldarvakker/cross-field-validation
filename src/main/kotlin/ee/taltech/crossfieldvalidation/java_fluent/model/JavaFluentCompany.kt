package ee.taltech.crossfieldvalidation.java_fluent.model

import ee.taltech.crossfieldvalidation.common.model.PersonType
import io.swagger.v3.oas.annotations.media.Schema

data class JavaFluentCompany(
    @field:Schema(allowableValues = ["COMPANY"])
    override val type: PersonType = PersonType.COMPANY,
    @field:Schema(description = "Company's name", example = "Maja Üks OÜ", minLength = 2, maxLength = 4)
    val name: String,
) : JavaFluentPerson()
