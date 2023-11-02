package ee.taltech.crossfieldvalidation.common.model.attributes

abstract class SocialMedia {
    abstract val platform: SocialMediaPlatforms
    abstract val profileUrl: String
}
