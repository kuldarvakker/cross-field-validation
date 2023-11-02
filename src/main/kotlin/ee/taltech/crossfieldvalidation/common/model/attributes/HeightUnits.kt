package ee.taltech.crossfieldvalidation.common.model.attributes

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@Schema(type = "String", description = "Height's unit", allowableValues = [])
enum class HeightUnits {
    @JsonProperty(value = "m")
    M,
    @JsonProperty(value = "cm")
    CM
}
