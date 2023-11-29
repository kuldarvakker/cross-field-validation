package ee.taltech.crossfieldvalidation.valiktor.model.company_b

import ee.taltech.crossfieldvalidation.checkNumericValueBounds
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.attributes.Gender
import ee.taltech.crossfieldvalidation.common.model.attributes.HeightUnits
import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMediaPlatforms.FACEBOOK
import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMediaPlatforms.TWITTER_X
import ee.taltech.crossfieldvalidation.valiktor.model.ValiktorAgencyForm
import ee.taltech.crossfieldvalidation.valiktor.withMessage
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Schema
import org.valiktor.functions.hasSize
import org.valiktor.functions.isIn
import org.valiktor.functions.isValid
import org.valiktor.functions.validate
import org.valiktor.functions.validateForEach
import org.valiktor.validate

data class ValiktorCompanyBAgencyForm(
    @field:Schema(allowableValues = ["COMPANY_B"])
    override val agency: Agency = Agency.COMPANY_B,
    @field:Schema(minLength = 1, maxLength = 50)
    override val firstName: String,
    @field:Schema(minLength = 1, maxLength = 50)
    override val lastName: String,
    @field:Schema(example = "MALE", allowableValues = ["MALE"])
    override val gender: Gender,
    @field:ArraySchema(minItems = 1, maxItems = 2)
    override val socialMedia: List<ValiktorCompanyBSocialMedia>,
    override val height: ValiktorCompanyBHeight,
) : ValiktorAgencyForm() {

    init {
        validate(this) { _ ->
            validate(ValiktorCompanyBAgencyForm::agency).isValid { it == Agency.COMPANY_B }
            validate(ValiktorCompanyBAgencyForm::firstName).hasSize(min = 1, max = 50)
            validate(ValiktorCompanyBAgencyForm::lastName).hasSize(min = 1, max = 50)
            validate(ValiktorCompanyBAgencyForm::gender).isIn(Gender.MALE)
            validate(ValiktorCompanyBAgencyForm::socialMedia).hasSize(1,2).validateForEach {
                validate(ValiktorCompanyBSocialMedia::platform).isIn(FACEBOOK, TWITTER_X)
                validate(ValiktorCompanyBSocialMedia::profileUrl).hasSize(1, 128)
            }
            validate(ValiktorCompanyBAgencyForm::height).validate {
                validate(ValiktorCompanyBHeight::unit).isIn(HeightUnits.CM)
                validate(ValiktorCompanyBHeight::value).withMessage("numeric value out of bounds (<3 digits>.<0 digits> expected)") { value ->
                    value?.let { checkNumericValueBounds(it, 3,0) } ?: true
                }
            }
        }
    }
}
