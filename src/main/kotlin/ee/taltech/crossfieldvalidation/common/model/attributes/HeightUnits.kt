package ee.taltech.crossfieldvalidation.common.model.attributes

import com.fasterxml.jackson.annotation.JsonProperty

enum class HeightUnits {
    @JsonProperty(value = "m")
    M,
    @JsonProperty(value = "cm")
    CM
}
