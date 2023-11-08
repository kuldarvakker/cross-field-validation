package ee.taltech.crossfieldvalidation.java_fluent.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.AgencyForm
import ee.taltech.crossfieldvalidation.java_fluent.model.company_a.JavaFluentCompanyAAgencyForm
import ee.taltech.crossfieldvalidation.java_fluent.model.company_b.JavaFluentCompanyBAgencyForm
import ee.taltech.crossfieldvalidation.java_fluent.model.general.JavaFluentGeneralAgencyForm

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "agency")
@JsonSubTypes(
    JsonSubTypes.Type(value = JavaFluentGeneralAgencyForm::class, name = "GENERAL"),
    JsonSubTypes.Type(value = JavaFluentCompanyAAgencyForm::class, name = "COMPANY_A"),
    JsonSubTypes.Type(value = JavaFluentCompanyBAgencyForm::class, name = "COMPANY_B")
)
abstract class JavaFluentAgencyForm: AgencyForm() {
    abstract val agency: Agency
}
