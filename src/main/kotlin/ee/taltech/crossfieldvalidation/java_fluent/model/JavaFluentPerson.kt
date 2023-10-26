package ee.taltech.crossfieldvalidation.java_fluent.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import ee.taltech.crossfieldvalidation.common.model.PersonType
import jakarta.validation.executable.ValidateOnExecution

@ValidateOnExecution
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes(
    JsonSubTypes.Type(value = JavaFluentPrivatePerson::class, name = "PRIVATE"),
    JsonSubTypes.Type(value = JavaFluentCompany::class, name = "COMPANY")
)
sealed class JavaFluentPerson {
    abstract val type: PersonType
}
