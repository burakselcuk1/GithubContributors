package com.example.contributors.ui.contributorsFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contributors.R
import com.example.contributors.adapter.ContributorAdapter
import com.example.contributors.base.BaseFragment
import com.example.contributors.common.enums.Status
import com.example.contributors.common.extensions.observe
import com.example.contributors.common.extensions.observeEvent
import com.example.contributors.common.tryOrLog
import com.example.contributors.common.utils.ProgressDialogUtil
import com.example.contributors.databinding.FragmentContributorsBinding

class ContributorsFragment : BaseFragment<FragmentContributorsBinding, ContributorViewModel>(
    layoutId = R.layout.fragment_contributors,
    viewModelClass = ContributorViewModel::class.java
) {

    private lateinit var adapter: ContributorAdapter
    var isProgress = false

    override fun onInitDataBinding() {
        observeEvent(viewModel.statusData) {
            tryOrLog {
                when (it) {
                    Status.LOADING -> {
                        if(!isProgress){
                            ProgressDialogUtil.showLoadingProgress(context = requireContext())
                            ProgressDialogUtil.start()
                        }
                    }
                    Status.SUCCESS -> {
                        isProgress = false;
                        ProgressDialogUtil.stop()
                    }
                    Status.ERROR -> {
                        isProgress = false;
                        ProgressDialogUtil.stop()
                    }
                }
            }
        }

        observe(viewModel.contributors){
            adapter = ContributorAdapter(it)
            binding.contributorRecyclerview.layoutManager = LinearLayoutManager(requireContext())
            binding.contributorRecyclerview.adapter = adapter

        }
    }


}