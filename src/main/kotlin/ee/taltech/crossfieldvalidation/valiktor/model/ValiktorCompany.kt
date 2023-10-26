package ee.taltech.crossfieldvalidation.valiktor.model

import ee.taltech.crossfieldvalidation.common.model.PersonType
import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotNull
import org.valiktor.validate

data class ValiktorCompany(
    override val type: PersonType = PersonType.COMPANY,
    val name: String,
) : ValiktorPerson() {
    init {
        validate(this) {
            validate(ValiktorCompany::name).isNotNull()
                .hasSize(min = 2, max = 4)
        }
    }
}
