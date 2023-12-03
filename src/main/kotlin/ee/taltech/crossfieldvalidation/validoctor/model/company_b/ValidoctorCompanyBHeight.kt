package ee.taltech.crossfieldvalidation.validoctor.model.company_b

import ee.taltech.crossfieldvalidation.common.model.attributes.Height
import ee.taltech.crossfieldvalidation.common.model.attributes.HeightUnits
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

data class ValidoctorCompanyBHeight(
    override val value: BigDecimal,
    @field:Schema(allowableValues = ["CM"])
    override val unit: HeightUnits
): Height()
