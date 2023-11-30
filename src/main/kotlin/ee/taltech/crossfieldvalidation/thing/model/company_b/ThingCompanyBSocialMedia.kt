package ee.taltech.crossfieldvalidation.thing.model.company_b

import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMedia
import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMediaPlatforms
import io.swagger.v3.oas.annotations.media.Schema

data class ThingCompanyBSocialMedia(
    @field:Schema(allowableValues = ["FACEBOOK", "TWITTER_X"])
    override val platform: SocialMediaPlatforms,
    @field:Schema(minLength = 1, maxLength = 128)
    override val profileUrl: String
): SocialMedia()
