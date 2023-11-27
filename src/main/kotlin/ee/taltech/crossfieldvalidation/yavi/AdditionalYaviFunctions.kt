package ee.taltech.crossfieldvalidation.yavi

import am.ik.yavi.constraint.CharSequenceConstraint

fun <T> CharSequenceConstraint<T, String>.length(minLength: Int, maxLength: Int): CharSequenceConstraint<T, String> {
    return this.greaterThanOrEqual(minLength).lessThanOrEqual(maxLength)
}
