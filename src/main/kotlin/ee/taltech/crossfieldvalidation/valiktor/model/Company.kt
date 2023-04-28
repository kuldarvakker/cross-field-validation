package ee.taltech.crossfieldvalidation.valiktor.model

import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotNull
import org.valiktor.validate

data class Company(
    override val type: PersonType = PersonType.COMPANY,
    val name: String,
) : Person() {
    init {
        validate(this) {
            validate(Company::name).isNotNull()
                .hasSize(min = 2, max = 4)
        }
    }
}
