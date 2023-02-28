package ee.taltech.crossfieldvalidation.java_fluent.model.attributes
import java.math.BigDecimal

data class Weight(
    val value: BigDecimal,
    val unit: Units
)