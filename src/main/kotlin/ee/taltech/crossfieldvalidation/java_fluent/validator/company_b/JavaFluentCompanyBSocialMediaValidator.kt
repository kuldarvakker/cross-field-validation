package ee.taltech.crossfieldvalidation.java_fluent.validator.company_b

import br.com.fluentvalidator.predicate.StringPredicate.stringSizeBetween
import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMediaPlatforms
import ee.taltech.crossfieldvalidation.java_fluent.model.company_b.JavaFluentCompanyBSocialMedia
import ee.taltech.crossfieldvalidation.java_fluent.validator.FluentValidator
import java.util.function.Predicate.isEqual

class JavaFluentCompanyBSocialMediaValidator(
    override val previousPath: String,
    override val countable: Boolean
) : FluentValidator<JavaFluentCompanyBSocialMedia>(previousPath, countable) {

    override fun rules() {
        setPropertyOnContext("socialMedia")

        fieldRuleOf(
            JavaFluentCompanyBSocialMedia::platform,
            isEqual<SocialMediaPlatforms>(SocialMediaPlatforms.FACEBOOK)
                .or(isEqual(SocialMediaPlatforms.TWITTER_X)),
            "Allowed values are: [FACEBOOK, TWITTER_X]"
        )

        fieldRuleOf(
            JavaFluentCompanyBSocialMedia::profileUrl,
            stringSizeBetween(1, 128),
            "size must be between 1 and 128"
        )
    }
}