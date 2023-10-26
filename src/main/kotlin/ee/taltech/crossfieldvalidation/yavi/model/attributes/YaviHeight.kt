package ee.taltech.crossfieldvalidation.yavi.model.attributes

import ee.taltech.crossfieldvalidation.common.model.attributes.HeightUnits
import java.math.BigDecimal

data class YaviHeight(
    val value: BigDecimal,
    val unit: HeightUnits
)
