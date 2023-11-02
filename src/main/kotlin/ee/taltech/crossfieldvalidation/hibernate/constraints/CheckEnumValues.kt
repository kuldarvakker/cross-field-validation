package ee.taltech.crossfieldvalidation.hibernate.constraints

import ee.taltech.crossfieldvalidation.common.model.attributes.Gender
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@MustBeDocumented
@Constraint(validatedBy = [CheckEnumValuesValidator::class])
annotation class CheckEnumValues(
    val message: String = "{hibernate.custom.constraints.CheckAllowedEnumValues.message}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val allowedValues: Array<String> = []
)

class CheckEnumValuesValidator : ConstraintValidator<CheckEnumValues, Enum<*>> {

    private lateinit var allowedValues: Set<String>

    override fun initialize(constraintAnnotation: CheckEnumValues) {
        this.allowedValues = constraintAnnotation.allowedValues.toSet()
    }

    override fun isValid(value: Enum<*>?, context: ConstraintValidatorContext): Boolean {
        value ?: return true

        val isValid = allowedValues.contains(value.name)

        if (!isValid) {
            val ctx = context.unwrap(HibernateConstraintValidatorContext::class.java)
            ctx.addMessageParameter("current", value.name)
        }

        return isValid
    }

}

