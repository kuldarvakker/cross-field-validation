package ee.taltech.crossfieldvalidation.ktor.model.attributes

import java.math.BigDecimal

data class Weight(
    val value: BigDecimal,
    val unit: Units
)