package ee.taltech.crossfieldvalidation.hibernate.model.attributes

import java.math.BigDecimal

data class Weight(
    val value: BigDecimal,
    val unit: Units
)