package org.meliapp.backend.dto.meli

import com.fasterxml.jackson.annotation.JsonProperty
import org.meliapp.backend.dto.product.ProductResponse

data class MeliSearchResponse(
    val results: List<ProductResponse>,
    @JsonProperty(value = "available_filters")
    val availableFilters: List<SearchFilter>
) {
    // Default constructor for Jackson
    constructor() : this(emptyList(), emptyList())
}
