package ee.taltech.crossfieldvalidation.hibernate.model.general

import ee.taltech.crossfieldvalidation.common.model.attributes.Weight
import ee.taltech.crossfieldvalidation.common.model.attributes.WeightUnits
import jakarta.validation.constraints.Digits
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal

@Validated
data class HibernateGeneralWeight(
    @field:Digits(integer = 3, fraction = 2)
    override val value: BigDecimal,
    override val unit: WeightUnits
): Weight()
