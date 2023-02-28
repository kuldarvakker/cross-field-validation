package ee.taltech.crossfieldvalidation.k_validation.model.attributes

import java.math.BigDecimal

data class Weight(
    val value: BigDecimal,
    val unit: Units
)