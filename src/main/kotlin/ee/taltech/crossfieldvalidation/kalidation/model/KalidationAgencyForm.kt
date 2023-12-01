package ee.taltech.crossfieldvalidation.kalidation.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.AgencyForm
import ee.taltech.crossfieldvalidation.kalidation.model.company_a.KalidationCompanyAAgencyForm
import ee.taltech.crossfieldvalidation.kalidation.model.company_b.KalidationCompanyBAgencyForm
import ee.taltech.crossfieldvalidation.kalidation.model.general.KalidationGeneralAgencyForm

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "agency")
@JsonSubTypes(
    JsonSubTypes.Type(value = KalidationGeneralAgencyForm::class, name = "GENERAL"),
    JsonSubTypes.Type(value = KalidationCompanyAAgencyForm::class, name = "COMPANY_A"),
    JsonSubTypes.Type(value = KalidationCompanyBAgencyForm::class, name = "COMPANY_B")
)
abstract class KalidationAgencyForm: AgencyForm() {
    abstract val agency: Agency
}
