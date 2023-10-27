package ee.taltech.crossfieldvalidation.hibernate.model.attributes

import ee.taltech.crossfieldvalidation.common.model.attributes.HeightUnits
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Digits
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal

@Validated
data class HibernateHeight(
    @field:Schema(example = "100.20")
    @field:Digits(integer = 3, fraction = 2)
    val value: BigDecimal,
    @field:Schema(example = "cm")
    val unit: HeightUnits
)
