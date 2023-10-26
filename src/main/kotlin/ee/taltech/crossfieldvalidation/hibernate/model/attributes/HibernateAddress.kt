package ee.taltech.crossfieldvalidation.hibernate.model.attributes

import jakarta.validation.constraints.Size
import org.springframework.validation.annotation.Validated

@Validated
data class HibernateAddress(
    @field:Size(min = 2)
    val street: String,
    @field:Size(min = 2)
    val city: String,
    @field:Size(min = 2)
    val state: String,
    @field:Size(min = 2)
    val country: String,
    @field:Size(min = 2)
    val postalCode: String
)
