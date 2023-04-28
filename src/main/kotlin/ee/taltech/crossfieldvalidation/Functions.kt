package ee.taltech.crossfieldvalidation

import java.math.BigDecimal

fun checkNumericValueBounds(value: BigDecimal, maxIntegerLimit: Int, maxFractionLimit: Int): Boolean {

    val integerPartLength = value.precision() - value.scale()
    val fractionPartLength = if (value.scale() < 0) 0 else value.scale()

    return maxIntegerLimit >= integerPartLength && maxFractionLimit >= fractionPartLength
}
