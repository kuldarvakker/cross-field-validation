package ee.taltech.crossfieldvalidation.konform.model

import ee.taltech.crossfieldvalidation.common.model.Agency
import io.konform.validation.Validation
import io.konform.validation.jsonschema.enum
import io.konform.validation.jsonschema.maxLength
import io.konform.validation.jsonschema.minLength
import io.swagger.v3.oas.annotations.media.Schema

data class KonformCompany(
    @field:Schema(allowableValues = ["COMPANY"])
    override val type: Agency = Agency.GENERAL,
    @field:Schema(description = "Company's name", example = "Maja Üks OÜ", minLength = 2, maxLength = 4)
    val name: String,
) : KonformPerson() {
    companion object {
        val validate = Validation<KonformCompany> {
            KonformCompany::type required { enum(Agency.GENERAL) }
            KonformCompany::name required {
                minLength(2)
                maxLength(4)
            }
        }
    }
}
