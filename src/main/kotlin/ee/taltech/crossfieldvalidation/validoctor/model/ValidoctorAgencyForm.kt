package ee.taltech.crossfieldvalidation.validoctor.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.AgencyForm
import ee.taltech.crossfieldvalidation.validoctor.model.company_a.ValidoctorCompanyAAgencyForm
import ee.taltech.crossfieldvalidation.validoctor.model.company_b.ValidoctorCompanyBAgencyForm
import ee.taltech.crossfieldvalidation.validoctor.model.general.ValidoctorGeneralAgencyForm

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "agency")
@JsonSubTypes(
    JsonSubTypes.Type(value = ValidoctorGeneralAgencyForm::class, name = "GENERAL"),
    JsonSubTypes.Type(value = ValidoctorCompanyAAgencyForm::class, name = "COMPANY_A"),
    JsonSubTypes.Type(value = ValidoctorCompanyBAgencyForm::class, name = "COMPANY_B")
)
abstract class ValidoctorAgencyForm: AgencyForm() {
    abstract val agency: Agency
}
