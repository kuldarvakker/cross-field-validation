package ee.taltech.crossfieldvalidation.konform.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.AgencyForm
import ee.taltech.crossfieldvalidation.java_fluent.model.company_a.JavaFluentCompanyAAgencyForm
import ee.taltech.crossfieldvalidation.java_fluent.model.company_b.JavaFluentCompanyBAgencyForm
import ee.taltech.crossfieldvalidation.java_fluent.model.general.JavaFluentGeneralAgencyForm
import ee.taltech.crossfieldvalidation.konform.model.company_a.KonformCompanyAAgencyForm
import ee.taltech.crossfieldvalidation.konform.model.company_b.KonformCompanyBAgencyForm

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "agency")
@JsonSubTypes(
    JsonSubTypes.Type(value = JavaFluentGeneralAgencyForm::class, name = "GENERAL"),
    JsonSubTypes.Type(value = KonformCompanyAAgencyForm::class, name = "COMPANY_A"),
    JsonSubTypes.Type(value = KonformCompanyBAgencyForm::class, name = "COMPANY_B")
)
abstract class KonformAgencyForm: AgencyForm() {
    abstract val agency: Agency
}
