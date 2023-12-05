package ee.taltech.crossfieldvalidation.validoctor.model.general

import ee.taltech.crossfieldvalidation.common.model.attributes.Weight
import ee.taltech.crossfieldvalidation.common.model.attributes.WeightUnits
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

data class ValidoctorGeneralAgencyWeight(
    override val value: BigDecimal,
    @field:Schema(allowableValues = ["KG"])
    override val unit: WeightUnits
) : Weight()