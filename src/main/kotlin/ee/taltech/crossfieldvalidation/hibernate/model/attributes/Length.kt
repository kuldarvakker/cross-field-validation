package ee.taltech.crossfieldvalidation.hibernate.model.attributes

import java.math.BigDecimal

data class Length(
    val value: BigDecimal,
    val unit: Units
)
