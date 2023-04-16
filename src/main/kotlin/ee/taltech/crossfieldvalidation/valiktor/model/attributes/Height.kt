package ee.taltech.crossfieldvalidation.valiktor.model.attributes

import java.math.BigDecimal

data class Height(
    val value: BigDecimal,
    val unit: Units
)
