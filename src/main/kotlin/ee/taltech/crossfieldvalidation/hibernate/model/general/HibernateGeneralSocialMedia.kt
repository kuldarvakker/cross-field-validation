package ee.taltech.crossfieldvalidation.hibernate.model.general

import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMedia

data class HibernateGeneralSocialMedia(
    override val platform: String,
    override val profileUrl: String
) : SocialMedia()
