package ee.taltech.crossfieldvalidation.hibernate.model.general

import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMedia
import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMediaPlatforms

data class HibernateGeneralSocialMedia(
    override val platform: SocialMediaPlatforms,
    override val profileUrl: String
) : SocialMedia()
