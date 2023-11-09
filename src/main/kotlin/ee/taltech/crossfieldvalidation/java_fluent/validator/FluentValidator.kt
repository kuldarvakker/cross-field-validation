package ee.taltech.crossfieldvalidation.java_fluent.validator

import br.com.fluentvalidator.AbstractValidator
import br.com.fluentvalidator.predicate.LogicalPredicate.isTrue
import br.com.fluentvalidator.predicate.LogicalPredicate.not
import br.com.fluentvalidator.predicate.ObjectPredicate.nullValue
import java.util.function.Predicate
import kotlin.reflect.KProperty1

abstract class FluentValidator<E> : AbstractValidator<E>() {

    protected fun <T> fieldRuleOf(
        field: KProperty1<E, T>,
        predicate: Predicate<T>,
        errorMessage: String
    ) {
        ruleFor(field)
            .must(predicate)
            .`when`(not(nullValue()))
            .withMessage(errorMessage)
            .withFieldName(field.name)
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
