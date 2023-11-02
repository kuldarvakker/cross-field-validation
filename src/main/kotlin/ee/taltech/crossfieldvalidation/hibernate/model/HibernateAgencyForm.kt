package ee.taltech.crossfieldvalidation.hibernate.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import ee.taltech.crossfieldvalidation.common.model.Agency

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes(
    JsonSubTypes.Type(value = HibernateGeneralAgencyForm::class, name = "GENERAL"),
    JsonSubTypes.Type(value = HibernatePrivatePerson::class, name = "COMPANY_A"),
    JsonSubTypes.Type(value = HibernateCompany::class, name = "COMPANY_B")
)
sealed class HibernateAgencyForm {
    abstract val agency: Agency
}
