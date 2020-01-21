package com.doctoremr.data.repositories

import com.doctoremr.data.network.NetworkApi
import com.doctoremr.data.network.SafeApiRequest

class PocRepository(private val api: NetworkApi) : SafeApiRequest() {

    suspend fun getAllergiesList(
    ) = apiRequest { api.getApiListDetail() }



}