package ee.taltech.crossfieldvalidation.common.model.attributes

import java.math.BigDecimal

abstract class Weight{
    abstract val value: BigDecimal
    abstract val unit: WeightUnits
}
