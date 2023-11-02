package ee.taltech.crossfieldvalidation.valiktor.model

import ee.taltech.crossfieldvalidation.common.model.Agency
import io.swagger.v3.oas.annotations.media.Schema
import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotNull
import org.valiktor.validate

data class ValiktorCompany(
    @field:Schema(allowableValues = ["COMPANY"])
    override val type: Agency = Agency.COMPANY,
    @field:Schema(description = "Company's name", example = "Maja Üks OÜ", minLength = 2, maxLength = 4)
    val name: String,
) : ValiktorPerson() {
    init {
        validate(this) {
            validate(ValiktorCompany::name).isNotNull()
                .hasSize(min = 2, max = 4)
        }
    }
}
