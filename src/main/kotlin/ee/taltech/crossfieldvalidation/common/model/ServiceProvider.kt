package ee.taltech.crossfieldvalidation.common.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(type = "String", description = "Type of a person", allowableValues = [])
enum class ServiceProvider {
    PRIVATE,
    COMPANY
}
