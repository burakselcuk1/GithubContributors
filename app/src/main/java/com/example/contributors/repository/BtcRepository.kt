package com.example.contributors.repository

import com.example.contributors.common.handleRequestFlow
import com.example.contributors.services.ApiImplTwo
import javax.inject.Inject


class BtcRepository @Inject constructor(private val btcApiImple: ApiImplTwo) {

    suspend fun getUsers() =  handleRequestFlow { btcApiImple.getBtc() }

}