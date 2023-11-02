package ee.taltech.crossfieldvalidation.hibernate.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.AgencyForm
import ee.taltech.crossfieldvalidation.hibernate.model.company_a.HibernateCompanyAAgencyForm
import ee.taltech.crossfieldvalidation.hibernate.model.company_b.HibernateCompanyBAgencyForm
import ee.taltech.crossfieldvalidation.hibernate.model.general.HibernateGeneralAgencyForm

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "agency")
@JsonSubTypes(
    JsonSubTypes.Type(value = HibernateGeneralAgencyForm::class, name = "GENERAL"),
    JsonSubTypes.Type(value = HibernateCompanyAAgencyForm::class, name = "COMPANY_A"),
    JsonSubTypes.Type(value = HibernateCompanyBAgencyForm::class, name = "COMPANY_B")
)
abstract class HibernateAgencyForm : AgencyForm() {
    abstract val agency: Agency
}
