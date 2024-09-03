package org.meliapp.backend.dto.meli

data class SearchFilter(
    val id: String,
    val type: String,
    val values: List<HashMap<String, String>>
)
