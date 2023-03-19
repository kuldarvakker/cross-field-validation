package ee.taltech.crossfieldvalidation.hibernate.model.attributes

import jakarta.validation.constraints.Digits
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal

@Validated
data class Height(
    @field:Digits(integer = 3, fraction = 2)
    val value: BigDecimal,
    val unit: Units
)
