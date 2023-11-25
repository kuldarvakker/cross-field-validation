package ee.taltech.crossfieldvalidation.konform.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.AgencyForm
import ee.taltech.crossfieldvalidation.konform.model.company_a.KonformCompanyAAgencyForm
import ee.taltech.crossfieldvalidation.konform.model.company_b.KonformCompanyBAgencyForm
import ee.taltech.crossfieldvalidation.konform.model.general.KonformGeneralAgencyForm

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "agency")
@JsonSubTypes(
    JsonSubTypes.Type(value = KonformGeneralAgencyForm::class, name = "GENERAL"),
    JsonSubTypes.Type(value = KonformCompanyAAgencyForm::class, name = "COMPANY_A"),
    JsonSubTypes.Type(value = KonformCompanyBAgencyForm::class, name = "COMPANY_B")
)
abstract class KonformAgencyForm: AgencyForm() {
    abstract val agency: Agency
}
