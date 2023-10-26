package ee.taltech.crossfieldvalidation.yavi.model.attributes

data class YaviAddress(
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val postalCode: String
)
