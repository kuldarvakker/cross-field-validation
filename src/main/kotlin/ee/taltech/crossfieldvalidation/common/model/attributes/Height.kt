package ee.taltech.crossfieldvalidation.common.model.attributes

import java.math.BigDecimal

abstract class Height {
    abstract val value: BigDecimal
    abstract val unit: HeightUnits
}
