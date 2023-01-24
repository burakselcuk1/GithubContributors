package com.example.contributors.services

import com.example.contributors.services.Api
import javax.inject.Inject

class ApiImpl @Inject constructor(private val api: Api) {

    suspend fun getContributors() = api.getContributors()

}