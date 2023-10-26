package ee.taltech.crossfieldvalidation.konform.model

import ee.taltech.crossfieldvalidation.common.model.PersonType
import io.konform.validation.Validation
import io.konform.validation.jsonschema.enum
import io.konform.validation.jsonschema.maxLength
import io.konform.validation.jsonschema.minLength

data class Company(
    override val type: PersonType = PersonType.COMPANY,
    val name: String
) : Person() {
    companion object {
        val validate = Validation<Company> {
            Company::type required { enum(PersonType.COMPANY) }
            Company::name required {
                minLength(2)
                maxLength(4)
            }
        }
    }
}
