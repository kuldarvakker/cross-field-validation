package ee.taltech.crossfieldvalidation.java_fluent.model.attributes
import ee.taltech.crossfieldvalidation.common.model.attributes.HeightUnits
import java.math.BigDecimal

data class JavaFluentHeight(
    val value: BigDecimal,
    val unit: HeightUnits
)
