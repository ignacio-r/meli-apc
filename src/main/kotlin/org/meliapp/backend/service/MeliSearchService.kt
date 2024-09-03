package org.meliapp.backend.service

import org.meliapp.backend.dto.meli.MeliSearchResponse
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class MeliSearchService(
    private val restClient: RestClient,
) {

    fun findByKeyword(keyword: String): MeliSearchResponse {
        val response=  restClient
            .get()
            .uri("/search?q=$keyword")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .body(object : ParameterizedTypeReference<MeliSearchResponse>() {})

        if (response != null) {
            return response
        }

        return MeliSearchResponse()
    }


}