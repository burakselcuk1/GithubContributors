package com.example.contributors.services

import com.example.contributors.model.ContributorsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("repos/JakeWharton/timber/stats/contributors")
    suspend fun getContributors(
    ): Response<ContributorsResponse>
}