package ee.taltech.crossfieldvalidation.konform.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import ee.taltech.crossfieldvalidation.common.model.PersonType

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes(
    JsonSubTypes.Type(value = PrivatePerson::class, name = "PRIVATE"),
    JsonSubTypes.Type(value = Company::class, name = "COMPANY")
)
sealed class Person {
    abstract val type: PersonType
}