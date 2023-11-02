package ee.taltech.crossfieldvalidation.common.model.attributes

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

@Schema(type = "String", description = "Height's unit", allowableValues = [])
enum class WeightUnits {
    @JsonProperty(value = "kg")
    KG
}
