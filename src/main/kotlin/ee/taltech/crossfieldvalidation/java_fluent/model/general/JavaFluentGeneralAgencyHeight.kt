package ee.taltech.crossfieldvalidation.java_fluent.model.general

import ee.taltech.crossfieldvalidation.common.model.attributes.Height
import ee.taltech.crossfieldvalidation.common.model.attributes.HeightUnits
import java.math.BigDecimal

data class JavaFluentGeneralAgencyHeight(
    override val value: BigDecimal,
    override val unit: HeightUnits
): Height()
