package ee.taltech.crossfieldvalidation.hibernate.model.general

import ee.taltech.crossfieldvalidation.common.model.attributes.Photo
import jakarta.validation.constraints.Size
import org.springframework.validation.annotation.Validated

@Validated
data class HibernateGeneralPhoto(
    @field:Size(min = 1, max = 28)
    override val typeOfPhoto: String,
    @field:Size(min = 1, max = 256)
    override val photoUrl: String
) : Photo()
