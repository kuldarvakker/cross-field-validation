package ee.taltech.crossfieldvalidation.hibernate.model.company_b

import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMedia
import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMediaPlatforms

data class HibernateCompanyBSocialMedia(
    override val platform: SocialMediaPlatforms,
    override val profileUrl: String
) : SocialMedia()
