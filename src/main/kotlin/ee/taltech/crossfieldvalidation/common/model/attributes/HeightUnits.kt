package ee.taltech.crossfieldvalidation.common.model.attributes

import io.swagger.v3.oas.annotations.media.Schema

@Schema(type = "String", description = "Height's unit", allowableValues = [])
enum class HeightUnits {
    M,
    CM
}
