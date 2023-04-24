package ee.taltech.crossfieldvalidation.konform.model.attributes

import java.math.BigDecimal

data class Height(
    val value: BigDecimal,
    val unit: Units
)
