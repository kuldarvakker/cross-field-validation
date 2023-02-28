package ee.taltech.crossfieldvalidation.ktor.model.attributes
data class Address(
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val postalCode: String
)
