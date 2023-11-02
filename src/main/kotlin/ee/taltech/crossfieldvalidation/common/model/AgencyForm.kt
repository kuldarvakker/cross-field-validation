package ee.taltech.crossfieldvalidation.common.model

import com.fasterxml.jackson.annotation.JsonIgnore
import ee.taltech.crossfieldvalidation.common.model.attributes.Gender
import ee.taltech.crossfieldvalidation.common.model.attributes.Height
import ee.taltech.crossfieldvalidation.common.model.attributes.Photo
import ee.taltech.crossfieldvalidation.common.model.attributes.SocialMedia
import ee.taltech.crossfieldvalidation.common.model.attributes.Weight
import java.time.LocalDate

abstract class AgencyForm {
    @field:JsonIgnore
    open val firstName: String? = null
    @field:JsonIgnore
    open val lastName: String? = null
    @field:JsonIgnore
    open val gender: Gender? = null
    @field:JsonIgnore
    open val birthDate: LocalDate? = null
    @field:JsonIgnore
    open val phoneNumber: String? = null
    @field:JsonIgnore
    open val email: String? = null
    @field:JsonIgnore
    open val socialMedia: List<SocialMedia>? = null
    @field:JsonIgnore
    open val currentLocation: String? = null
    @field:JsonIgnore
    open val height: Height? = null
    @field:JsonIgnore
    open val weight: Weight? = null
    @field:JsonIgnore
    open val shoeSize: Int? = null
    @field:JsonIgnore
    open val photos: List<Photo>? = null
}
