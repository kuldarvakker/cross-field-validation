package ee.taltech.crossfieldvalidation.hibernate

data class ValidationErrors (
    val errors: List<ValidationError>
)