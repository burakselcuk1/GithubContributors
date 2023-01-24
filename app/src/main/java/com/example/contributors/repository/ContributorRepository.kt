package com.example.contributors.repository

import com.example.contributors.common.handleRequestFlow
import com.example.contributors.services.ApiImpl
import javax.inject.Inject


class ContributorRepository @Inject constructor(private val contributorsApiImple: ApiImpl) {

    suspend fun getContributors() =  handleRequestFlow { contributorsApiImple.getContributors() }

}