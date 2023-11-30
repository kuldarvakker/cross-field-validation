package ee.taltech.crossfieldvalidation.thing.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.AgencyForm
import ee.taltech.crossfieldvalidation.thing.model.company_a.ThingCompanyAAgencyForm
import ee.taltech.crossfieldvalidation.thing.model.company_b.ThingCompanyBAgencyForm
import ee.taltech.crossfieldvalidation.thing.model.general.ThingGeneralAgencyForm

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "agency")
@JsonSubTypes(
    JsonSubTypes.Type(value = ThingGeneralAgencyForm::class, name = "GENERAL"),
    JsonSubTypes.Type(value = ThingCompanyAAgencyForm::class, name = "COMPANY_A"),
    JsonSubTypes.Type(value = ThingCompanyBAgencyForm::class, name = "COMPANY_B")
)
abstract class ThingAgencyForm: AgencyForm() {
    abstract val agency: Agency
}
