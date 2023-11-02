package ee.taltech.crossfieldvalidation.valiktor.model.attributes

import ee.taltech.crossfieldvalidation.common.model.attributes.WeightUnits
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

data class ValiktorWeight(
    @field:Schema(example = "0.75")
    val value: BigDecimal,
    @field:Schema(example = "kg")
    val unit: WeightUnits
)