package ee.taltech.crossfieldvalidation.hibernate.model.attributes

import jakarta.validation.constraints.Digits
import java.math.BigDecimal

data class Weight(
    @Digits(integer = 3, fraction = 2)
    val value: BigDecimal,
    val unit: Units
)