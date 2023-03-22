package ee.taltech.crossfieldvalidation.java_fluent.model

import ee.taltech.crossfieldvalidation.java_fluent.model.attributes.Address
import ee.taltech.crossfieldvalidation.java_fluent.model.attributes.Height
import ee.taltech.crossfieldvalidation.java_fluent.model.attributes.Weight

data class PrivatePerson(
    override val type: PersonType = PersonType.PRIVATE,
//    @field:Size(min = 2, max = 4)
    val firstName: String,
//    @field:Size(min = 5, max = 10)
    val lastName: String,
//    @field:Size(max = 10)
    val phoneNumber: String?,
    val emails: List<String>?,
//    @field:Valid
    val address: Address,
//    @field:Valid
    val height: Height,
//    @field:Valid
    val weight: Weight
) : Person() {

//    @AssertTrue(message = "Phone or Email must be present")
//    fun isAtLeastPhoneOrEmailPresent(): Boolean = false
}


