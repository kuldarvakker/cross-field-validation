package ee.taltech.crossfieldvalidation.valiktor.model.attributes

import ee.taltech.crossfieldvalidation.common.model.attributes.WeightUnits
import java.math.BigDecimal

data class ValiktorWeight(
    val value: BigDecimal,
    val unit: WeightUnits
)
