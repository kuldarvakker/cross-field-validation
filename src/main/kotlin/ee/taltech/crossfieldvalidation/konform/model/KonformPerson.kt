package ee.taltech.crossfieldvalidation.konform.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import ee.taltech.crossfieldvalidation.common.model.ServiceProvider

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes(
    JsonSubTypes.Type(value = KonformPrivatePerson::class, name = "PRIVATE"),
    JsonSubTypes.Type(value = KonformCompany::class, name = "COMPANY")
)
sealed class KonformPerson {
    abstract val type: ServiceProvider
}
