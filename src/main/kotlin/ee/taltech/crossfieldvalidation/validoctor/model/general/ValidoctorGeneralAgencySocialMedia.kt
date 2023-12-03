package ee.taltech.crossfieldvalidation.validoctor.model.general

import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMedia
import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMediaPlatforms
import io.swagger.v3.oas.annotations.media.Schema

data class ValidoctorGeneralAgencySocialMedia(
    @field:Schema(allowableValues = ["FACEBOOK", "TIKTOK", "TWITTER_X", "INSTAGRAM", "LINKEDIN"])
    override val platform: SocialMediaPlatforms,
    @field:Schema(minLength = 1, maxLength = 128)
    override val profileUrl: String
) : SocialMedia()
