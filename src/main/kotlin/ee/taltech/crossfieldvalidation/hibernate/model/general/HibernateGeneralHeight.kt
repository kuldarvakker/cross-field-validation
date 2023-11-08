package ee.taltech.crossfieldvalidation.hibernate.model.general

import ee.taltech.crossfieldvalidation.common.model.attributes.Height
import ee.taltech.crossfieldvalidation.common.model.attributes.HeightUnits
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Digits
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal

@Validated
data class HibernateGeneralHeight(
    @field:Digits(integer = 3, fraction = 2)
    override val value: BigDecimal,
    @field:Schema(allowableValues = ["M", "CM"])
    override val unit: HeightUnits
): Height()
