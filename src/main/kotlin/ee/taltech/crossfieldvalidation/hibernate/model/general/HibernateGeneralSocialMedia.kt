package ee.taltech.crossfieldvalidation.hibernate.model.general

import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMedia
import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMediaPlatforms
import jakarta.validation.constraints.Size

data class HibernateGeneralSocialMedia(
    override val platform: SocialMediaPlatforms,
    @field:Size(min = 1, max = 128)
    override val profileUrl: String
) : SocialMedia()
