package ee.taltech.crossfieldvalidation.validoctor.model.general

import ee.taltech.crossfieldvalidation.common.model.attributes.Height
import ee.taltech.crossfieldvalidation.common.model.attributes.HeightUnits
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

data class ValidoctorGeneralAgencyHeight(
    override val value: BigDecimal,
    @field:Schema(allowableValues = ["M", "CM"])
    override val unit: HeightUnits
): Height()
