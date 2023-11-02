package ee.taltech.crossfieldvalidation.hibernate.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import ee.taltech.crossfieldvalidation.common.model.ServiceProvider

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes(
    JsonSubTypes.Type(value = HibernatePrivatePerson::class, name = "PRIVATE"),
    JsonSubTypes.Type(value = HibernateCompany::class, name = "COMPANY")
)
sealed class HibernatePerson {
    abstract val type: ServiceProvider
}
