package ee.taltech.crossfieldvalidation.hibernate.model.attributes

import ee.taltech.crossfieldvalidation.common.model.attributes.WeightUnits
import jakarta.validation.constraints.Digits
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal

@Validated
data class HibernateWeight(
    @field:Digits(integer = 3, fraction = 2)
    val value: BigDecimal,
    val unit: WeightUnits
)
