package ee.taltech.crossfieldvalidation.java_fluent.model

import ee.taltech.crossfieldvalidation.common.model.ServiceProvider
import ee.taltech.crossfieldvalidation.java_fluent.model.attributes.JavaFluentAddress
import ee.taltech.crossfieldvalidation.java_fluent.model.attributes.JavaFluentHeight
import ee.taltech.crossfieldvalidation.java_fluent.model.attributes.JavaFluentWeight
import io.swagger.v3.oas.annotations.media.Schema

data class JavaFluentPrivatePerson(
    @field:Schema(allowableValues = ["PRIVATE"])
    override val type: ServiceProvider = ServiceProvider.PRIVATE,
    @field:Schema(example = "Mari", minLength = 2, maxLength = 4)
    val firstName: String,
    @field:Schema(example = "Maasikas", minLength = 5, maxLength = 10)
    val lastName: String,
    @field:Schema(example = "+37254541010", maxLength = 10)
    val phoneNumber: String?,
    @field:Schema(example = """["email@taltech.ee", "email2@taltech.ee"]""") // TODO define list elem size
    val emails: List<String>?,
    @field:Schema(description = "Person's address")
    val address: JavaFluentAddress,
    @field:Schema(description = "Person's height")
    val height: JavaFluentHeight,
    @field:Schema(description = "Person's weight")
    val weight: JavaFluentWeight
) : JavaFluentPerson()
