package ee.taltech.crossfieldvalidation.valiktor

import ee.taltech.crossfieldvalidation.checkLocalDateIsAfterOrEqualsTo
import org.valiktor.Constraint
import org.valiktor.Validator
import java.time.LocalDate

data class AfterOrEqual<T>(val constraintValue: T) : Constraint

fun <E> Validator<E>.Property<LocalDate?>.isAfterOrEqual(constraintValue: LocalDate) =
    this.validate(AfterOrEqual(constraintValue)) { it == null || checkLocalDateIsAfterOrEqualsTo(it, constraintValue) }
