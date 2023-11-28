package ee.taltech.crossfieldvalidation.akkurate.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import ee.taltech.crossfieldvalidation.akkurate.model.company_a.AkkurateCompanyAAgencyForm
import ee.taltech.crossfieldvalidation.akkurate.model.company_b.AkkurateCompanyBAgencyForm
import ee.taltech.crossfieldvalidation.akkurate.model.general.AkkurateGeneralAgencyForm
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.AgencyForm
import ee.taltech.crossfieldvalidation.konform.model.company_a.KonformCompanyAAgencyForm
import ee.taltech.crossfieldvalidation.konform.model.company_b.KonformCompanyBAgencyForm
import ee.taltech.crossfieldvalidation.konform.model.general.KonformGeneralAgencyForm

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "agency")
@JsonSubTypes(
    JsonSubTypes.Type(value = AkkurateGeneralAgencyForm::class, name = "GENERAL"),
    JsonSubTypes.Type(value = AkkurateCompanyAAgencyForm::class, name = "COMPANY_A"),
    JsonSubTypes.Type(value = AkkurateCompanyBAgencyForm::class, name = "COMPANY_B")
)
abstract class AkkurateAgencyForm: AgencyForm() {
    abstract val agency: Agency
}
