package ee.taltech.crossfieldvalidation.yavi.model.company_b

import am.ik.yavi.builder.ValidatorBuilder
import am.ik.yavi.core.ViolationMessage
import ee.taltech.crossfieldvalidation.checkNumericValueBounds
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.attributes.Gender
import ee.taltech.crossfieldvalidation.common.model.attributes.HeightUnits
import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMediaPlatforms.FACEBOOK
import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMediaPlatforms.TWITTER_X
import ee.taltech.crossfieldvalidation.yavi.length
import ee.taltech.crossfieldvalidation.yavi.model.YaviAgencyForm
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema

data class YaviCompanyBAgencyForm(
    @field:Schema(allowableValues = ["COMPANY_B"])
    override val agency: Agency = Agency.COMPANY_B,
    @field:Schema(minLength = 1, maxLength = 50)
    override val firstName: String,
    @field:Schema(minLength = 1, maxLength = 50)
    override val lastName: String,
    @field:Schema(example = "MALE", allowableValues = ["MALE"])
    override val gender: Gender,
    @field:ArraySchema(minItems = 1, maxItems = 2)
    override val socialMedia: List<YaviCompanyBSocialMedia>,
    override val height: YaviCompanyBHeight,
) : YaviAgencyForm()

val yaviCompanyBAgencyFormValidator = ValidatorBuilder.of<YaviCompanyBAgencyForm>()
    .constraint(YaviCompanyBAgencyForm::firstName, "firstName") { c -> c.length(1, 50) }
    .constraint(YaviCompanyBAgencyForm::lastName, "lastName") { c -> c.length(1, 50) }
    .constraint(YaviCompanyBAgencyForm::socialMedia, "socialMedia") { c -> c.lessThanOrEqual(2).greaterThanOrEqual(1) }
    .nest(YaviCompanyBAgencyForm::height, "height") { e ->
        e.constraint(YaviCompanyBHeight::value, "value") { c ->
            c.predicate(
                { checkNumericValueBounds(it, 3, 0) },
                "",
                "numeric value out of bounds (<3 digits>.<0 digits> expected)"
            )
        }
            .constraintOnTarget(
                { it.unit in listOf(HeightUnits.CM) },
                "unit",
                ViolationMessage.of(
                    "",
                    "Allowed values are: [CM]"
                )
            )
    }
    .forEach(YaviCompanyBAgencyForm::socialMedia, "socialMedia") { e ->
        e.constraint(YaviCompanyBSocialMedia::profileUrl, "profileUrl") { c ->
            c.length(1, 128)
        }
            .constraintOnTarget(
                { it.platform in listOf(FACEBOOK, TWITTER_X) },
                "platform",
                ViolationMessage.of(
                    "",
                    "Allowed values are: [FACEBOOK, TWITTER_X]"
                )
            )
    }
    .build()
