package ee.taltech.crossfieldvalidation.validoctor.model.attributes

import java.math.BigDecimal

data class Length(
    val value: BigDecimal,
    val unit: Units
)
