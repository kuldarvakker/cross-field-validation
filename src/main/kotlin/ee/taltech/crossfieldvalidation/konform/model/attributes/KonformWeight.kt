package ee.taltech.crossfieldvalidation.konform.model.attributes

import ee.taltech.crossfieldvalidation.common.model.attributes.WeightUnits
import java.math.BigDecimal

data class KonformWeight(
    val value: BigDecimal,
    val unit: WeightUnits
)
