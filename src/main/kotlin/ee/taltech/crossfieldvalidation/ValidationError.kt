package ee.taltech.crossfieldvalidation

data class ValidationError(
    val field: String,
    val message: String
)
