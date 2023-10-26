package ee.taltech.crossfieldvalidation.yavi.model.attributes

import ee.taltech.crossfieldvalidation.common.model.attributes.WeightUnits
import java.math.BigDecimal

data class YaviWeight(
    val value: BigDecimal,
    val unit: WeightUnits
)
