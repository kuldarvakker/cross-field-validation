package ee.taltech.crossfieldvalidation.konform.model.attributes

data class KonformAddress(
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val postalCode: String
)
