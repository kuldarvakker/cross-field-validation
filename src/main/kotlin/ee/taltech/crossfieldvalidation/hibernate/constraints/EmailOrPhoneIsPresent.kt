package ee.taltech.crossfieldvalidation.hibernate.constraints

import ee.taltech.crossfieldvalidation.hibernate.model.company_a.HibernateCompanyAAgencyForm
import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@MustBeDocumented
@Constraint(validatedBy = [EmailOrPhoneIsPresentValidator::class])
annotation class EmailOrPhoneIsPresent(
    val message: String = "{hibernate.custom.constraints.PhoneOrEmailIsPresent.message}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class EmailOrPhoneIsPresentValidator : ConstraintValidator<EmailOrPhoneIsPresent, HibernateCompanyAAgencyForm> {
    override fun isValid(value: HibernateCompanyAAgencyForm?, context: ConstraintValidatorContext): Boolean {
        value ?: return true

        val isValid = !value.phoneNumber.isNullOrBlank() || !value.email.isNullOrBlank()

        if (!isValid) {
            context.disableDefaultConstraintViolation()

            val ctx = context.unwrap(HibernateConstraintValidatorContext::class.java)
            ctx.buildConstraintViolationWithTemplate(context.defaultConstraintMessageTemplate)
                .addPropertyNode("phoneNumber")
                .addConstraintViolation()
        }

        return isValid
    }
}
