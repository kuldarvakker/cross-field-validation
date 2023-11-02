package ee.taltech.crossfieldvalidation.common.model

import io.swagger.v3.oas.annotations.media.Schema

@Schema(type = "String", description = "Provider of the service", allowableValues = [])
enum class Agency {
    GENERAL,
    COMPANY_A,
    COMPANY_B
}
