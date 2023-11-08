package ee.taltech.crossfieldvalidation.java_fluent.model.general

import ee.taltech.crossfieldvalidation.common.model.Agency
import ee.taltech.crossfieldvalidation.common.model.attributes.Gender
import ee.taltech.crossfieldvalidation.java_fluent.model.JavaFluentAgencyForm
import java.time.LocalDate

data class JavaFluentGeneralAgencyForm(
    override val agency: Agency = Agency.GENERAL,
    override val firstName: String,
    override val lastName: String,
    override val gender: Gender,
    override val birthDate: LocalDate,
    override val phoneNumber: String,
    override val email: String,
    override val socialMedia: List<JavaFluentGeneralAgencySocialMedia>,
    override val height: JavaFluentGeneralAgencyHeight,
    override val weight: JavaFluentGeneralAgencyWeight
) : JavaFluentAgencyForm()
