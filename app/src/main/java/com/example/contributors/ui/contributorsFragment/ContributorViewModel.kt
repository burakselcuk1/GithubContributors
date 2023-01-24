package com.example.contributors.ui.contributorsFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.contributors.base.BaseViewModel
import com.example.contributors.common.enums.Status
import com.example.contributors.model.ContributorsResponse
import com.example.contributors.repository.ContributorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContributorViewModel @Inject constructor(private val repository: ContributorRepository): BaseViewModel() {

    private  val _contributors = MutableLiveData<ContributorsResponse>()
    val contributors : LiveData<ContributorsResponse> = _contributors

    private var _statusData = MutableLiveData<Status>()
    val statusData: LiveData<Status> = _statusData

    init {
        getUsers()
    }

    fun getUsers() {

        GlobalScope.launch {
            repository.getContributors()
                .catch {
                    _statusData.postValue(Status.ERROR)
                }
                .collect {
                    when (it.status) {
                        Status.LOADING -> {
                            _statusData.postValue(Status.LOADING)
                        }
                        Status.SUCCESS -> {
                            it.data?.body().let {
                                _contributors.postValue(it)
                                _statusData.postValue(Status.SUCCESS)
                            }
                        }
                        Status.ERROR -> {
                            _statusData.postValue(Status.ERROR)
                        }
                    }
                }
        }
    }
}