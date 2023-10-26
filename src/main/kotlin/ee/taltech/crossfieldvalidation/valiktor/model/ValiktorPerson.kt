package ee.taltech.crossfieldvalidation.valiktor.model

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import ee.taltech.crossfieldvalidation.common.model.PersonType
import jakarta.validation.executable.ValidateOnExecution

@ValidateOnExecution
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes(
    JsonSubTypes.Type(value = ValiktorPrivatePerson::class, name = "PRIVATE"),
    JsonSubTypes.Type(value = ValiktorCompany::class, name = "COMPANY")
)
sealed class ValiktorPerson {
    abstract val type: PersonType
}
