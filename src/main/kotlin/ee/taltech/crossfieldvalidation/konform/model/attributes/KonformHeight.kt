package ee.taltech.crossfieldvalidation.konform.model.attributes

import ee.taltech.crossfieldvalidation.common.model.attributes.HeightUnits
import java.math.BigDecimal

data class KonformHeight(
    val value: BigDecimal,
    val unit: HeightUnits
)
