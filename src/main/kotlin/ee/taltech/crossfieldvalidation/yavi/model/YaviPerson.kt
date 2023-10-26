package ee.taltech.crossfieldvalidation.yavi.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import ee.taltech.crossfieldvalidation.common.model.PersonType
import jakarta.validation.executable.ValidateOnExecution

@ValidateOnExecution
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes(
    JsonSubTypes.Type(value = YaviPrivatePerson::class, name = "PRIVATE"),
    JsonSubTypes.Type(value = YaviCompany::class, name = "COMPANY")
)
sealed class YaviPerson {
    abstract val type: PersonType
}
