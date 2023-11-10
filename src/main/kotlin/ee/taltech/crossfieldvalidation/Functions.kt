package ee.taltech.crossfieldvalidation

import java.math.BigDecimal
import java.util.function.Predicate

fun checkNumericValueBounds(value: BigDecimal, maxIntegerLimit: Int, maxFractionLimit: Int): Boolean {

    val integerPartLength = value.precision() - value.scale()
    val fractionPartLength = if (value.scale() < 0) 0 else value.scale()

    return maxIntegerLimit >= integerPartLength && maxFractionLimit >= fractionPartLength
}

fun checkNumericValueBounds(maxIntegerLimit: Int, maxFractionLimit: Int): Predicate<BigDecimal?> {
    return Predicate { value ->
        value?.run {
            checkNumericValueBounds(this, maxIntegerLimit, maxFractionLimit)
        } ?: true
    }
}
