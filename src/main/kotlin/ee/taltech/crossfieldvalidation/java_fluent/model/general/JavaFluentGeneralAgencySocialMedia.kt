package ee.taltech.crossfieldvalidation.java_fluent.model.general

import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMedia
import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMediaPlatforms

data class JavaFluentGeneralAgencySocialMedia(
    override val platform: SocialMediaPlatforms,
    override val profileUrl: String
) : SocialMedia()
