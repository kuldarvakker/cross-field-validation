package ee.taltech.crossfieldvalidation.hibernate.model.attributes

import ee.taltech.crossfieldvalidation.common.model.attributes.WeightUnits
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Digits
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal

@Validated
data class HibernateWeight(
    @field:Schema(example = "0.75")
    @field:Digits(integer = 3, fraction = 2)
    val value: BigDecimal,
    @field:Schema(example = "kg")
    val unit: WeightUnits
)
