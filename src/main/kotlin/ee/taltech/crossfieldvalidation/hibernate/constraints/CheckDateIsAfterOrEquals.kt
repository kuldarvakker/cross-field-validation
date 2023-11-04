package ee.taltech.crossfieldvalidation.hibernate.constraints

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import java.time.LocalDate
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@MustBeDocumented
@Constraint(validatedBy = [CheckDateIsAfterOrEqualsValidator::class])
annotation class CheckDateIsAfterOrEquals(
    val message: String = "{hibernate.custom.constraints.CheckDateIsAfterOrEquals.message}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val year: Long = 1970,
    val month: Long = 1,
    val day: Long = 1,
)

class CheckDateIsAfterOrEqualsValidator : ConstraintValidator<CheckDateIsAfterOrEquals, LocalDate> {

    private lateinit var localDate: LocalDate

    override fun initialize(constraintAnnotation: CheckDateIsAfterOrEquals) {
        this.localDate = LocalDate.of(
            constraintAnnotation.year.toInt(),
            constraintAnnotation.month.toInt(),
            constraintAnnotation.day.toInt()
        )
    }
    override fun isValid(value: LocalDate?, context: ConstraintValidatorContext): Boolean {
        value ?: return true

        val isValid = value.isAfter(localDate) || value.isEqual(localDate)

        return isValid
    }
}
