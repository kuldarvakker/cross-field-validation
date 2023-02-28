package ee.taltech.crossfieldvalidation.valiktor.model.attributes

import java.math.BigDecimal

data class Length(
    val value: BigDecimal,
    val unit: Units
)
