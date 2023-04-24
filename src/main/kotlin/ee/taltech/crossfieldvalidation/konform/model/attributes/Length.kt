package ee.taltech.crossfieldvalidation.konform.model.attributes

import java.math.BigDecimal

data class Length(
    val value: BigDecimal,
    val unit: Units
)
