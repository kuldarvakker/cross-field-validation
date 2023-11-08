package ee.taltech.crossfieldvalidation.common.model.attributes

import io.swagger.v3.oas.annotations.media.Schema

@Schema(type = "String", description = "Social media platform", allowableValues = [])
enum class SocialMediaPlatforms {
    FACEBOOK,
    TIKTOK,
    TWITTER_X,
    INSTAGRAM,
    LINKEDIN,
}
