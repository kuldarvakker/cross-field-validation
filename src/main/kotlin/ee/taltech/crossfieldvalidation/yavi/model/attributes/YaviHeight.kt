package ee.taltech.crossfieldvalidation.yavi.model.attributes

import ee.taltech.crossfieldvalidation.common.model.attributes.HeightUnits
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

data class YaviHeight(
    @field:Schema(example = "100.20")
    val value: BigDecimal,
    @field:Schema(example = "cm")
    val unit: HeightUnits
)
