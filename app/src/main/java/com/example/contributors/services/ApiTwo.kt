package com.example.contributors.services

import com.example.contributors.model.BtcResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiTwo {

    @GET("api/rates")
    suspend fun getBtc(
    ): Response<BtcResponse>
}