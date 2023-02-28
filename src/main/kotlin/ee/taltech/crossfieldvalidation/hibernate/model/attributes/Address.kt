package ee.taltech.crossfieldvalidation.hibernate.model.attributes

import jakarta.validation.constraints.Size
import org.springframework.validation.annotation.Validated

@Validated
data class Address(
    @Size(min = 1)
    val street: String,
    @Size(min = 1)
    val city: String,
    @Size(min = 1)
    val state: String,
    @Size(min = 1)
    val country: String,
    @Size(min = 1)
    val postalCode: String
)
