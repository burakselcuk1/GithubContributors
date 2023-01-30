package com.example.contributors.services

import javax.inject.Inject

class ApiImplTwo @Inject constructor(private val api: ApiTwo) {

    suspend fun getBtc() = api.getBtc()

}