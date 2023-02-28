package ee.taltech.crossfieldvalidation.yavi.model.attributes

import java.math.BigDecimal

data class Length(
    val value: BigDecimal,
    val unit: Units
)
