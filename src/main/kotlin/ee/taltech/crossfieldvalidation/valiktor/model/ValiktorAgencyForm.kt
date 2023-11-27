package ee.taltech.crossfieldvalidation.valiktor.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.AgencyForm
import ee.taltech.crossfieldvalidation.valiktor.model.company_a.ValiktorCompanyAAgencyForm
import ee.taltech.crossfieldvalidation.valiktor.model.company_b.ValiktorCompanyBAgencyForm
import ee.taltech.crossfieldvalidation.valiktor.model.general.ValiktorGeneralAgencyForm

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "agency")
@JsonSubTypes(
    JsonSubTypes.Type(value = ValiktorGeneralAgencyForm::class, name = "GENERAL"),
    JsonSubTypes.Type(value = ValiktorCompanyAAgencyForm::class, name = "COMPANY_A"),
    JsonSubTypes.Type(value = ValiktorCompanyBAgencyForm::class, name = "COMPANY_B")
)
abstract class ValiktorAgencyForm: AgencyForm() {
    abstract val agency: Agency
}
