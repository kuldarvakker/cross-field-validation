package ee.taltech.crossfieldvalidation.hibernate

data class ValidationError (
    val code: String,
    val arguments: List<String>
)
