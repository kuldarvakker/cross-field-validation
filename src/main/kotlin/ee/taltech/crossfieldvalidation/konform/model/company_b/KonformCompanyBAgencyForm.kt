package ee.taltech.crossfieldvalidation.konform.model.company_b

import ee.taltech.crossfieldvalidation.checkNumericValueBounds
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.attributes.Gender
import ee.taltech.crossfieldvalidation.common.model.attributes.HeightUnits
import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMediaPlatforms.FACEBOOK
import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMediaPlatforms.TWITTER_X
import ee.taltech.crossfieldvalidation.konform.model.KonformAgencyForm
import io.konform.validation.Validation
import io.konform.validation.jsonschema.enum
import io.konform.validation.jsonschema.maxItems
import io.konform.validation.jsonschema.maxLength
import io.konform.validation.jsonschema.minItems
import io.konform.validation.jsonschema.minLength
import io.konform.validation.onEach
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema

data class KonformCompanyBAgencyForm(
    @field:Schema(allowableValues = ["COMPANY_B"])
    override val agency: Agency = Agency.COMPANY_B,
    @field:Schema(minLength = 1, maxLength = 50)
    override val firstName: String,
    @field:Schema(minLength = 1, maxLength = 50)
    override val lastName: String,
    @field:Schema(example = "MALE", allowableValues = ["MALE"])
    override val gender: Gender,
    @field:ArraySchema(minItems = 1, maxItems = 2)
    override val socialMedia: List<KonformCompanyBSocialMedia>,
    override val height: KonformCompanyBHeight,
) : KonformAgencyForm() {
    companion object {
        val validate = Validation<KonformCompanyBAgencyForm> {
            KonformCompanyBAgencyForm::agency { enum(Agency.COMPANY_B) }
            KonformCompanyBAgencyForm::firstName {
                minLength(1)
                maxLength(50)
            }
            KonformCompanyBAgencyForm::lastName {
                minLength(1)
                maxLength(50)
            }
            KonformCompanyBAgencyForm::gender { enum(Gender.MALE) }
            KonformCompanyBAgencyForm::socialMedia {
                minItems(1)
                maxItems(2)
                onEach {
                    KonformCompanyBSocialMedia::platform {
                        enum(FACEBOOK, TWITTER_X)
                    }
                    KonformCompanyBSocialMedia::profileUrl {
                        minLength(1)
                        maxLength(128)
                    }
                }
            }
            KonformCompanyBAgencyForm::height {
                KonformCompanyBHeight::unit { enum(HeightUnits.CM) }
                KonformCompanyBHeight::value {
                    addConstraint("numeric value out of bounds (<3 digits>.<0 digits> expected)") {
                        checkNumericValueBounds(it, 3, 0)
                    }
                }
            }
        }
    }
}
