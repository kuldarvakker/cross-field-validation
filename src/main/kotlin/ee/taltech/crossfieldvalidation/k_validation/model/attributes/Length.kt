package ee.taltech.crossfieldvalidation.k_validation.model.attributes

import java.math.BigDecimal

data class Length(
    val value: BigDecimal,
    val unit: Units
)
