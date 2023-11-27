package ee.taltech.crossfieldvalidation.yavi.model.general

import am.ik.yavi.builder.ValidatorBuilder
import am.ik.yavi.core.ViolationMessage
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.attributes.Gender
import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMediaPlatforms
import ee.taltech.crossfieldvalidation.yavi.length
import ee.taltech.crossfieldvalidation.yavi.model.YaviAgencyForm
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class YaviGeneralAgencyForm(
    @field:Schema(allowableValues = ["GENERAL"])
    override val agency: Agency = Agency.GENERAL,
    @field:Schema(minLength = 1, maxLength = 50)
    override val firstName: String,
    @field:Schema(minLength = 1, maxLength = 50)
    override val lastName: String,
    @field:Schema(allowableValues = ["MALE", "FEMALE"])
    override val gender: Gender,
    override val birthDate: LocalDate,
    @field:Schema(minLength = 5, maxLength = 35)
    override val phoneNumber: String,
    @field:Schema(minLength = 1, maxLength = 128)
    override val email: String,
    @field:ArraySchema(minItems = 1, maxItems = 10)
    override val socialMedia: List<YaviGeneralAgencySocialMedia>,
    override val height: YaviGeneralAgencyHeight,
    override val weight: YaviGeneralAgencyWeight
) : YaviAgencyForm()

val yaviGeneralAgencyFormValidator = ValidatorBuilder.of<YaviGeneralAgencyForm>()
    .constraint(YaviGeneralAgencyForm::firstName, "firstName") { c -> c.length(1, 50) }
    .constraint(YaviGeneralAgencyForm::lastName, "lastName") { c -> c.length(1, 50)}
    .constraintOnTarget(
        { it.gender in listOf(Gender.MALE, Gender.FEMALE) },
        "gender",
        ViolationMessage.of(
            "",
            "Allowed values are: [MALE, FEMALE]"
        )
    )
    .constraint(YaviGeneralAgencyForm::phoneNumber, "phoneNumber") { c -> c.length(5, 35) }
    .constraint(YaviGeneralAgencyForm::email, "email") { c -> c.length(1, 128) }
    .constraint(YaviGeneralAgencyForm::socialMedia, "socialMedia") { c -> c.lessThanOrEqual(10).greaterThanOrEqual(1) }
    .forEach(YaviGeneralAgencyForm::socialMedia, "socialMedia") { e ->
        e.constraint(YaviGeneralAgencySocialMedia::profileUrl, "profileUrl") { c ->
            c.length(1, 128)
        }
            .constraintOnTarget(
                { it.platform in SocialMediaPlatforms.values() },
                "platform",
                ViolationMessage.of(
                    "",
                    "Allowed values are: {${SocialMediaPlatforms.values()}}"
                )
            )
    }
    .build()
