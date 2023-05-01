package ee.taltech.crossfieldvalidation.valiktor

import org.valiktor.Constraint
import org.valiktor.DefaultConstraintViolation
import org.valiktor.Validator
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

data class Custom(val message: String) : Constraint {
    override val messageKey: String = "org.valiktor.constraints.Custom.message"

    override val messageParams: Map<String, *>
        get() = mapOf(this::message.name to this.message)
}

fun <E, T> Validator<E>.Property<T?>.withMessage(message: String, block: (T?) -> Boolean) =
    this.validate(Custom(message)) { block.invoke(it) }

fun <E, T, A> Validator<E>.Property<T?>.include(property: KProperty1<E, A?>): Pair<Validator<E>.Property<T?>, Validator<E>.Property<A & Any>> {
    val a = Validator(this.obj).Property(this.obj, property)
    return Pair(this, a)
}

fun <E, T, A, B> Validator<E>.Property<T?>.include(property: KProperty1<E, A?>, property2: KProperty1<E, B?>): Triple<Validator<E>.Property<T?>, Validator<E>.Property<A & Any>, Validator<E>.Property<B & Any>> {
    val a = Validator(this.obj).Property(this.obj, property)
    val b = Validator(this.obj).Property(this.obj, property2)
    return Triple(this, a, b)
}

fun <E, T, A> Pair<Validator<E>.Property<T?>, Validator<E>.Property<A & Any>>.withMessage(message: String, block: (T?, A?) -> Boolean) {
    this.first.validate(Custom(message)) { block.invoke(it, this.second.property.get(this.second.obj)) }
}

fun <E, T, A, B> Triple<Validator<E>.Property<T?>, Validator<E>.Property<A & Any>, Validator<E>.Property<B & Any>>.withMessage(message: String, block: (T?, A?, B?) -> Boolean) {
    this.first.validate(Custom(message)) { block.invoke(it, this.second.property.get(this.second.obj), this.third.property.get(this.third.obj)) }
}

inline fun <reified T : Any, R> T.getPrivateProperty(name: String): R? =
    T::class
        .memberProperties
        .firstOrNull { it.name == name }
        ?.apply { isAccessible = true }
        ?.get(this) as? R

fun <E, A, B> Validator<E>.validate(str: String, property: KProperty1<E, A?>, property2: KProperty1<E, B?>): Pair<Validator<E>.Property<A>, Validator<E>.Property<B & Any>> {
    val a = this.Property(this.getPrivateProperty("obj")!!, property)
    val b = this.Property(this.getPrivateProperty("obj")!!, property2)
    return Pair(a as Validator<E>.Property<A>, b)
}

fun <E, T> Validator<E>.Property<T?>.withFieldName(str: String, block: Validator<T>.(T) -> Unit): Validator<E>.Property<T?> {
    val value = this.property.get(this.obj)
    if (value != null) {
        this.addConstraintViolations(
            Validator(value as T).apply { block(value) }.constraintViolations.map {
                DefaultConstraintViolation(
                    property = "${str}.${it.property}",
                    value = it.value,
                    constraint = it.constraint
                )
            }
        )
    }
    return this
}
