package ee.taltech.crossfieldvalidation.konform.model.attributes

import java.math.BigDecimal

data class Weight(
    val value: BigDecimal,
    val unit: Units
)