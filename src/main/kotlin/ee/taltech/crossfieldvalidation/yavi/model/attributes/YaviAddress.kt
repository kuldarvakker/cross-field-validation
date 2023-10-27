package ee.taltech.crossfieldvalidation.yavi.model.attributes

import io.swagger.v3.oas.annotations.media.Schema

data class YaviAddress(
    @field:Schema(example = "Raja 4c", minLength = 2)
    val street: String,
    @field:Schema(example = "Tallinn", minLength = 2)
    val city: String,
    @field:Schema(example = "Harjumaa", minLength = 2)
    val state: String,
    @field:Schema(example = "Estonia", minLength = 2)
    val country: String,
    @field:Schema(example = "12616", minLength = 2)
    val postalCode: String
)
