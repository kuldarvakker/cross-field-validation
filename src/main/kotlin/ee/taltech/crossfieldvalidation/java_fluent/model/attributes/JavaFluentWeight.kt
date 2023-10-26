package ee.taltech.crossfieldvalidation.java_fluent.model.attributes
import ee.taltech.crossfieldvalidation.common.model.attributes.WeightUnits
import java.math.BigDecimal

data class JavaFluentWeight(
    val value: BigDecimal,
    val unit: WeightUnits
)
