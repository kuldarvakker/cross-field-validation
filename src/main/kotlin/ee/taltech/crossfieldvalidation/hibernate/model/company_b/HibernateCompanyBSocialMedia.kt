package ee.taltech.crossfieldvalidation.hibernate.model.company_b

import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMedia
import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMediaPlatforms
import ee.taltech.crossfieldvalidation.hibernate.constraints.CheckEnumValues
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.URL
import org.springframework.validation.annotation.Validated

@Validated
data class HibernateCompanyBSocialMedia(
    @field:Schema(allowableValues = ["FACEBOOK", "TWITTER_X"])
    @field:CheckEnumValues(allowedValues = ["FACEBOOK", "TWITTER_X"])
    override val platform: SocialMediaPlatforms,
    @field:URL
    @field:Size(min = 1, max = 128)
    override val profileUrl: String
) : SocialMedia()
