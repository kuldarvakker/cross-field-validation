package ee.taltech.crossfieldvalidation.hibernate.model.general

import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMedia
import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMediaPlatforms
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size

data class HibernateGeneralSocialMedia(
    @field:Schema(allowableValues = ["FACEBOOK", "TIKTOK", "TWITTER_X", "INSTAGRAM", "LINKEDIN"])
    override val platform: SocialMediaPlatforms,
    @field:Size(min = 1, max = 128)
    override val profileUrl: String
) : SocialMedia()
