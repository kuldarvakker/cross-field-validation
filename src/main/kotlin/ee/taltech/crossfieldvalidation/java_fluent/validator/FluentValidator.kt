package ee.taltech.crossfieldvalidation.java_fluent.validator

import br.com.fluentvalidator.AbstractValidator
import br.com.fluentvalidator.predicate.LogicalPredicate.isTrue
import br.com.fluentvalidator.predicate.LogicalPredicate.not
import br.com.fluentvalidator.predicate.ObjectPredicate.nullValue
import java.util.function.Predicate
import kotlin.reflect.KProperty1

abstract class FluentValidator<E : Any>(
    open val previousPath: String? = null,
    open val countable: Boolean = false
) : AbstractValidator<E>() {

    protected fun <T> fieldRuleOf(
        field: KProperty1<E, T>,
        predicate: Predicate<T>,
        errorMessage: String,
    ) {
        val fieldName = if (previousPath == null) {
            field.name
        } else if (countable) {
            arrayOf("$previousPath[$counter]", field.name)
                .joinToString(".")
        } else {
            arrayOf(previousPath, field.name)
                .joinToString(".")
        }

        ruleFor(field)
            .must(predicate)
            .`when`(not(nullValue()))
            .withMessage(errorMessage)
            .withFieldName(fieldName)
    }

    protected fun entityRuleOf(
        fieldName: String,
        predicate: (E) -> Boolean,
        message: String
    ) {
        ruleFor {
            predicate.invoke(it)
        }.must(isTrue())
            .withMessage(message)
            .withFieldName(fieldName)
    }
}
