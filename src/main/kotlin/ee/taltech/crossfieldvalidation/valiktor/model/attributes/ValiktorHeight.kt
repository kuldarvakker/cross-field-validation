package ee.taltech.crossfieldvalidation.valiktor.model.attributes

import ee.taltech.crossfieldvalidation.common.model.attributes.HeightUnits
import java.math.BigDecimal

data class ValiktorHeight(
    val value: BigDecimal,
    val unit: HeightUnits
)
