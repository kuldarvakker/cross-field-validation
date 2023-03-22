package ee.taltech.crossfieldvalidation.java_fluent.model

import ee.taltech.crossfieldvalidation.java_fluent.model.attributes.Address

data class Company(
    override val type: PersonType = PersonType.COMPANY,
//    @field:Size(min = 1)
    val name: String,
//    @field:NotNull
//    @field:Size(max = 10)
    val phoneNumber: String,
    val emails: List<String>,
//    @field:NotEmpty
    val addresses: List<Address>
) : Person() {

//    @AssertTrue
//    fun isNameContainingCompanyType(): Boolean { return name.contains(regex = "OÜ|AS".toRegex()) }
}