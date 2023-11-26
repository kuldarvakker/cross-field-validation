package ee.taltech.crossfieldvalidation.konform

import io.konform.validation.ValidationBuilder
import io.konform.validation.jsonschema.maxItems
import io.konform.validation.jsonschema.maxLength
import io.konform.validation.jsonschema.minItems
import io.konform.validation.jsonschema.minLength

fun ValidationBuilder<String>.length(minLength: Int, maxLength: Int) {
    minLength(minLength) hint "size must be between $minLength and $maxLength"
    maxLength(maxLength) hint "size must be between $minLength and $maxLength"
}

fun<T> ValidationBuilder<List<T>>.size(minItems: Int, maxItems: Int) {
    minItems(minItems) hint "size must be between $minItems and $maxItems"
    maxItems(maxItems) hint "size must be between $minItems and $maxItems"
}
