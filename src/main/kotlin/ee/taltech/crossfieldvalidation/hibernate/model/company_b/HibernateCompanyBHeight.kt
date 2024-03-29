package ee.taltech.crossfieldvalidation.hibernate.model.company_b

import ee.taltech.crossfieldvalidation.common.model.attributes.Height
import ee.taltech.crossfieldvalidation.common.model.attributes.HeightUnits
import ee.taltech.crossfieldvalidation.hibernate.constraints.CheckEnumValues
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Digits
import java.math.BigDecimal

data class HibernateCompanyBHeight(
    @field:Digits(integer = 3, fraction = 0)
    override val value: BigDecimal,
    @field:Schema(allowableValues = ["CM"])
    @field:CheckEnumValues(allowedValues = ["CM"])
    override val unit: HeightUnits
): Height()
