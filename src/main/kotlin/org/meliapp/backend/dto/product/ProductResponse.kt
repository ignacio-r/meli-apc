package org.meliapp.backend.dto.product

data class ProductResponse(val id: String, val title: String, val price: Double) {
    constructor(): this("", "", 0.0)
}
