package ee.taltech.crossfieldvalidation.validoctor.model.attributes

import java.math.BigDecimal

data class Weight(
    val value: BigDecimal,
    val unit: Units
)