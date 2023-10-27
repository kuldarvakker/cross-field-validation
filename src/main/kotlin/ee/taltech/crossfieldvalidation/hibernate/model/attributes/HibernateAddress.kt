package ee.taltech.crossfieldvalidation.hibernate.model.attributes

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size
import org.springframework.validation.annotation.Validated

@Validated
data class HibernateAddress(
    @field:Schema(example = "Raja 4c")
    @field:Size(min = 2)
    val street: String,
    @field:Schema(example = "Tallinn")
    @field:Size(min = 2)
    val city: String,
    @field:Schema(example = "Harjumaa")
    @field:Size(min = 2)
    val state: String,
    @field:Schema(example = "Estonia")
    @field:Size(min = 2)
    val country: String,
    @field:Schema(example = "12616")
    @field:Size(min = 2)
    val postalCode: String
)
