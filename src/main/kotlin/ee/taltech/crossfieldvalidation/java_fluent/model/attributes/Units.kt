package ee.taltech.crossfieldvalidation.java_fluent.model.attributes
import com.fasterxml.jackson.annotation.JsonProperty

enum class Units {
    @JsonProperty(value = "kg")
    KG,
    @JsonProperty(value = "m")
    M,
    @JsonProperty(value = "cm")
    CM
}