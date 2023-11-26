package ee.taltech.crossfieldvalidation.yavi.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.AgencyForm
import ee.taltech.crossfieldvalidation.yavi.model.company_a.YaviCompanyAAgencyForm
import ee.taltech.crossfieldvalidation.yavi.model.company_b.YaviCompanyBAgencyForm
import ee.taltech.crossfieldvalidation.yavi.model.general.YaviGeneralAgencyForm

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "agency")
@JsonSubTypes(
    JsonSubTypes.Type(value = YaviGeneralAgencyForm::class, name = "GENERAL"),
    JsonSubTypes.Type(value = YaviCompanyAAgencyForm::class, name = "COMPANY_A"),
    JsonSubTypes.Type(value = YaviCompanyBAgencyForm::class, name = "COMPANY_B")
)
abstract class YaviAgencyForm: AgencyForm() {
    abstract val agency: Agency
}
