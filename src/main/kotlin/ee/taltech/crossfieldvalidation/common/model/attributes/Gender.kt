package ee.taltech.crossfieldvalidation.common.model.attributes

import io.swagger.v3.oas.annotations.media.Schema

@Schema(type = "String", description = "Candidate's gender", allowableValues = [])
enum class Gender {
    MALE,
    FEMALE
}
